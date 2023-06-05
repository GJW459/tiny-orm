package com.gjw.orm.mapping;

import com.gjw.orm.session.Configuration;

import java.util.Map;

/**
 * @author wei
 */
public class MapperStatement {

    private String id;

    private Configuration configuration;

    private SqlCommandType sqlCommandType;

    private String parameterType;

    private String resultType;

    private String sql;

    private Map<Integer,String> parameter;


    MapperStatement() {
        // constructor disabled
    }

    public static class Builder{

        private MapperStatement mapperStatement = new MapperStatement();

        public Builder(Configuration configuration,String id,SqlCommandType sqlCommandType,String parameterType,
                       String resultType,String sql,Map<Integer,String> parameter) {
            mapperStatement.configuration = configuration;
            mapperStatement.id = id;
            mapperStatement.sqlCommandType=sqlCommandType;
            mapperStatement.parameterType = parameterType;
            mapperStatement.resultType = resultType;
            mapperStatement.sql =sql;
            mapperStatement.parameter = parameter;
        }

        public MapperStatement build(){
            assert mapperStatement.configuration!=null;
            assert mapperStatement.id!=null;
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

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<Integer, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<Integer, String> parameter) {
        this.parameter = parameter;
    }
}
