package com.wei.orm.binding;

import com.wei.orm.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wei
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterfaces;

    private final Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<T> mapperInterfaces) {
        this.mapperInterfaces = mapperInterfaces;
    }

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterfaces,methodCache);
        return (T) Proxy.newProxyInstance(mapperInterfaces.getClassLoader(), new Class[]{mapperInterfaces}, mapperProxy);
    }

    public Map<Method, MapperMethod> getMethodCache() {
        return methodCache;
    }
}
