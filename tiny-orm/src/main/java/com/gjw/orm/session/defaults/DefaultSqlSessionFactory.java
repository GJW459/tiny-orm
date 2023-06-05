package com.gjw.orm.session.defaults;

import com.gjw.orm.session.Configuration;
import com.gjw.orm.session.SqlSession;
import com.gjw.orm.session.SqlSessionFactory;

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
