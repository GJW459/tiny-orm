package com.gjw.orm.session.defaults;

import com.gjw.orm.binding.MapperRegistry;
import com.gjw.orm.mapping.MapperStatement;
import com.gjw.orm.session.Configuration;
import com.gjw.orm.session.SqlSession;

/**
 * @author wei
 */
public class DefaultSqlSession implements SqlSession {


    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + "方法：" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MapperStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你被代理了！" + "\n方法：" + statement + "\n入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql().replace("\n",""));
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
