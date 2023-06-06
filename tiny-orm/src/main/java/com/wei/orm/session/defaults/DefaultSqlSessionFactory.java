package com.wei.orm.session.defaults;

import com.wei.orm.session.Configuration;
import com.wei.orm.session.SqlSession;
import com.wei.orm.session.SqlSessionFactory;

/**
 * @author wei
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
