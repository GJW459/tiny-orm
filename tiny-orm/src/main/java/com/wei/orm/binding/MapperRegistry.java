package com.wei.orm.binding;

import cn.hutool.core.lang.ClassScanner;
import com.wei.orm.session.Configuration;
import com.wei.orm.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author wei
 * MapperFactory 注册中心
 */
public class MapperRegistry {

    private final Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 将已添加的映射器代理注册到HashMap
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(final Class<T> type, final SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause" + e, e);
        }
    }

    public <T> void addMapper(final Class<T> type) {
        /**
         * mapper 必须是接口才能够注册
         */
        if (type.isInterface()) {
            if (hasMapper(type)) {
                // 如果重复添加了，报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry");
            }
            // 注册映射器代理工厂
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public <T> boolean hasMapper(final Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public void addMappers(final String packageName) {
        final Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (final Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }
}
