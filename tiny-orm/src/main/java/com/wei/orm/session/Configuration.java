package com.wei.orm.session;

import com.wei.orm.binding.MapperRegistry;
import com.wei.orm.datasource.druid.DruidDataSourceFactory;
import com.wei.orm.datasource.pooled.PooledDataSourceFactory;
import com.wei.orm.datasource.unpooled.UnPooledDataSourceFactory;
import com.wei.orm.executor.Executor;
import com.wei.orm.executor.SimpleExecutor;
import com.wei.orm.executor.resultset.DefaultResultSetHandler;
import com.wei.orm.executor.resultset.ResultSetHandler;
import com.wei.orm.executor.statement.PreparedStatementHandler;
import com.wei.orm.executor.statement.StatementHandler;
import com.wei.orm.mapping.BoundSql;
import com.wei.orm.mapping.Environment;
import com.wei.orm.mapping.MapperStatement;
import com.wei.orm.transaction.Transaction;
import com.wei.orm.transaction.jdbc.JdbcTransactionFactory;
import com.wei.orm.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wei
 * 配置
 */
public class Configuration {

    /**
     * 映射注册中心
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的SQL语句
     */
    protected final Map<String, MapperStatement> mapperStatements = new HashMap<>();


    /**
     * 环境 数据库连接信息
     */
    protected Environment environment;

    /**
     * 类型别名注册机
     */
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnPooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

    public void addMappers(final String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MapperStatement ms) {
        mapperStatements.put(ms.getId(), ms);
    }

    public MapperStatement getMappedStatement(String id) {
        return mapperStatements.get(id);
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    /**
     * 创建结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MapperStatement mapperStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mapperStatement, boundSql);
    }

    /**
     * 创建语句处理器
     */
    public StatementHandler newStatement(Executor executor, MapperStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, ms, parameter, resultHandler, boundSql);
    }

    /**
     * 生产执行器
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }
}
