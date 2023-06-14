package com.wei.orm.executor.statement;

import com.wei.orm.executor.Executor;
import com.wei.orm.mapping.BoundSql;
import com.wei.orm.mapping.MapperStatement;
import com.wei.orm.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author wei
 * 简单语句处理器
 */
public class SimpleStatementHandler extends BaseStatementHandler{
    public SimpleStatementHandler(Executor executor, MapperStatement mapperStatement, Object parameterObject, ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mapperStatement, parameterObject, resultHandler, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        // N/A
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        String sql = boundSql.getSql();
        statement.execute(sql);
        return resultSetHandler.handleResultSets(statement);
    }
}
