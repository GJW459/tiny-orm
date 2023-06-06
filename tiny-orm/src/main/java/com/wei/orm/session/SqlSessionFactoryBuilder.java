package com.wei.orm.session;

import com.wei.orm.builder.xml.XMLConfigBuilder;
import com.wei.orm.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * @author wei
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration){
        return new DefaultSqlSessionFactory(configuration);
    }
}
