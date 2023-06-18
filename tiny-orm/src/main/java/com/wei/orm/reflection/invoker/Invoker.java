package com.wei.orm.reflection.invoker;

/**
 * @author wei
 * 调用者
 */
public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();
}
