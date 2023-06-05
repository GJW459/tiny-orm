package com.gjw.orm.builder;

import com.gjw.orm.session.Configuration;

/**
 * @author wei
 * 构建器的基类
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
