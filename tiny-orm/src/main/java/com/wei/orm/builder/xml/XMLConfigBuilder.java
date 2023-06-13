package com.wei.orm.builder.xml;

import com.wei.orm.builder.BaseBuilder;
import com.wei.orm.datasource.DataSourceFactory;
import com.wei.orm.io.Resources;
import com.wei.orm.mapping.BoundSql;
import com.wei.orm.mapping.Environment;
import com.wei.orm.mapping.MapperStatement;
import com.wei.orm.mapping.SqlCommandType;
import com.wei.orm.session.Configuration;
import com.wei.orm.transaction.TransactionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.Reader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wei
 * xml配置构造器
 */
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 1. 调用父类构造器
        super(new Configuration());
        // 2. dom4j 处理xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析配置：类型别名、插件、对象工厂、对象包装工厂、设置、环境、类型转换、映射器
     *
     * @return
     */
    public Configuration parse() {
        try {
            // 解析映射器
            mapperElement(root.element("mappers"));
            // 解析环境
            environmentElement(root.element("environments"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration Cause：" + e, e);
        }
        return configuration;
    }

    private void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element element : mapperList) {
            String resource = element.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element rootElement = document.getRootElement();
            // 命名空间
            String namespace = rootElement.attributeValue("namespace");
            // select
            List<Element> selectNodes = rootElement.elements("select");
            for (Element node : selectNodes) {
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getText();
                // ? 匹配
                Map<Integer, String> parameter = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for (int i = 1; matcher.find(); i++) {
                    String g1 = matcher.group(1);
                    String g2 = matcher.group(2);
                    parameter.put(i, g2);
                    sql = sql.replace(g1, "?");
                    sql = sql.replace("\n","");
                }
                String msId = namespace + "." + id;
                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
                BoundSql boundSql = new BoundSql(sql, parameter, parameterType, resultType);
                MapperStatement mapperStatement = new MapperStatement.Builder(configuration, msId, sqlCommandType,
                        boundSql).build();
                // 添加解析SQL
                configuration.addMappedStatement(mapperStatement);
            }
            configuration.addMapper(Resources.classForName(namespace));
        }
    }

    /**
     * <environments default="development">
     * <environment id="development">
     * <transactionManager type="JDBC">
     * <property name="..." value="..."/>
     * </transactionManager>
     * <dataSource type="POOLED">
     * <property name="driver" value="${driver}"/>
     * <property name="url" value="${url}"/>
     * <property name="username" value="${username}"/>
     * <property name="password" value="${password}"/>
     * </dataSource>
     * </environment>
     * </environments>
     */
    private void environmentElement(Element context) throws Exception {
        String environment = context.attributeValue("default");
        List<Element> environmentElementList = context.elements("environment");
        for (Element element : environmentElementList) {
            String id = element.attributeValue("id");
            if (environment.equals(id)) {
                // 事务
                TransactionFactory transactionFactory = (TransactionFactory) typeAliasRegistry.resolveAlias(element.element("transactionManager").attributeValue("type")).newInstance();
                Element dataSourceElement = element.element("dataSource");
                List<Element> propertyList = dataSourceElement.elements("property");
                Properties properties = new Properties();
                for (Element property : propertyList) {
                    properties.put(property.attributeValue("name"), property.attributeValue("value"));
                }
                DataSourceFactory dataSourceFactory = (DataSourceFactory) typeAliasRegistry.resolveAlias(dataSourceElement.attributeValue("type")).newInstance();
                dataSourceFactory.setProperties(properties);
                DataSource dataSource = dataSourceFactory.getDataSource();
                // 后续可能会有多个事务管理器
                configuration.setEnvironment(new Environment.Builder(id)
                        .transactionFactory(transactionFactory)
                        .dataSource(dataSource).build());
            }
        }
    }

}
