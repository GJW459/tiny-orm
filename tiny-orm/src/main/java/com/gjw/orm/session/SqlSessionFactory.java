package com.gjw.orm.session;

/**
 * @author wei
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
