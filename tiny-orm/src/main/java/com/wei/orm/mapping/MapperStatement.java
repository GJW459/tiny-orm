package com.wei.orm.mapping;

import com.wei.orm.session.Configuration;

/**
 * @author wei
 */
public class MapperStatement {

    private String id;

    private Configuration configuration;

    private SqlCommandType sqlCommandType;

    private BoundSql boundSql;


    MapperStatement() {
        // constructor disabled
    }

    public static class Builder {

        private final MapperStatement mapperStatement = new MapperStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, BoundSql boundSql) {
            mapperStatement.configuration = configuration;
            mapperStatement.id = id;
            mapperStatement.sqlCommandType = sqlCommandType;
            mapperStatement.boundSql = boundSql;
        }

        public MapperStatement build() {
            assert mapperStatement.configuration != null;
            assert mapperStatement.id != null;
            return mapperStatement;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public BoundSql getBoundSql() {
        return boundSql;
    }

    public void setBoundSql(BoundSql boundSql) {
        this.boundSql = boundSql;
    }
}
