package com.wei.orm.reflection.wrapper;

import com.wei.orm.reflection.MetaObject;

/**
 * @author wei
 * 对象包装工厂
 */
public interface ObjectWrapperFactory {

    /**
     * 判断是否有包装器
     * @param object
     * @return
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     * @param metaObject
     * @param object
     * @return
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject,Object object);
}
