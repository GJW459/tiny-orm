package com.wei.orm.session;

/**
 * @author wei
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
