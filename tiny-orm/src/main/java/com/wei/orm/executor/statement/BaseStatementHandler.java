package com.wei.orm.executor.statement;

import com.wei.orm.executor.Executor;
import com.wei.orm.executor.resultset.ResultSetHandler;
import com.wei.orm.mapping.BoundSql;
import com.wei.orm.mapping.MapperStatement;
import com.wei.orm.session.Configuration;
import com.wei.orm.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author wei
 * 语句处理器抽象类
 */
public abstract class BaseStatementHandler implements StatementHandler {

    protected final Configuration configuration;
    protected final Executor executor;
    protected final MapperStatement mapperStatement;
    protected final Object parameterObject;
    protected final ResultSetHandler resultSetHandler;
    protected BoundSql boundSql;

    public BaseStatementHandler(Executor executor, MapperStatement mapperStatement, Object parameterObject, ResultHandler resultHandler, BoundSql boundSql) {
        this.configuration = mapperStatement.getConfiguration();
        this.executor = executor;
        this.mapperStatement = mapperStatement;
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
        this.resultSetHandler = configuration.newResultSetHandler(executor, mapperStatement, boundSql);
    }

    @Override
    public Statement prepare(Connection connection) throws SQLException {
        Statement statement = null;
        try {
            // 实例化 Statement
            statement = instantiateStatement(connection);
            // 参数设置 可以被抽取 提供配置
            statement.setQueryTimeout(350);
            statement.setFetchSize(10000);
            return statement;
        } catch (Exception e) {
            throw new RuntimeException("Error preparing statement. Cause:" + e, e);
        }
    }

    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;
}
