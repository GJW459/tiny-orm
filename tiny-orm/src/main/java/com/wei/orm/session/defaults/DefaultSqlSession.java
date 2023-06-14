package com.wei.orm.session.defaults;

import com.wei.orm.executor.Executor;
import com.wei.orm.mapping.BoundSql;
import com.wei.orm.mapping.Environment;
import com.wei.orm.mapping.MapperStatement;
import com.wei.orm.session.Configuration;
import com.wei.orm.session.SqlSession;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wei
 */
public class DefaultSqlSession implements SqlSession {


    private final Configuration configuration;

    private final Executor executor;


    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + "方法：" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MapperStatement ms = configuration.getMappedStatement(statement);
        List<T> res = executor.query(ms, parameter, Executor.NO_RESULT_HANDLER, ms.getBoundSql());
        return res.get(0);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
