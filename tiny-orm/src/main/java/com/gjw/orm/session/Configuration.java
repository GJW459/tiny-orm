package com.gjw.orm.session;

import com.gjw.orm.binding.MapperRegistry;
import com.gjw.orm.mapping.MapperStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wei
 * 配置
 */
public class Configuration {

    /**
     * 映射注册中心
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的SQL语句
     */
    protected final Map<String, MapperStatement> mapperStatements = new HashMap<>();

    public void addMappers(final String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T>T getMapper(Class<T> type,SqlSession sqlSession){
        return mapperRegistry.getMapper(type,sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MapperStatement ms){
        mapperStatements.put(ms.getId(),ms);
    }

    public MapperStatement getMappedStatement(String id){
        return mapperStatements.get(id);
    }
}
