package com.wei.orm.mapping;

import java.util.Map;

/**
 * @author wei
 * 绑定的SQL,是从SqlSource而来，将动态内容都处理完成得到的SQL语句字符串，其中包括?,还有绑定的参数
 */
public class BoundSql {

    private String sql;

    private Map<Integer,String> parameterMapping;

    private String parameterType;

    private String resultType;

    public BoundSql(String sql, Map<Integer, String> parameterMapping, String parameterType, String resultType) {
        this.sql = sql;
        this.parameterMapping = parameterMapping;
        this.parameterType = parameterType;
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<Integer, String> getParameterMapping() {
        return parameterMapping;
    }

    public void setParameterMapping(Map<Integer, String> parameterMapping) {
        this.parameterMapping = parameterMapping;
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
}
