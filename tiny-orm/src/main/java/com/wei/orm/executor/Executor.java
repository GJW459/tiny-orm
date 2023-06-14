package com.wei.orm.executor;

import com.wei.orm.mapping.BoundSql;
import com.wei.orm.mapping.MapperStatement;
import com.wei.orm.session.ResultHandler;
import com.wei.orm.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * @author wei
 * 执行器：定义的接口包括事务相关的处理方法和执行SQL查询的操作
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MapperStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);
}
