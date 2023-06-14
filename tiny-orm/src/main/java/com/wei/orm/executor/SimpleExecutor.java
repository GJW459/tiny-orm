package com.wei.orm.executor;

import com.wei.orm.executor.statement.StatementHandler;
import com.wei.orm.mapping.BoundSql;
import com.wei.orm.mapping.MapperStatement;
import com.wei.orm.session.Configuration;
import com.wei.orm.session.ResultHandler;
import com.wei.orm.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author wei
 * 简单执行器
 */
public class SimpleExecutor extends BaseExecutor {
    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MapperStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {

        try{
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatement(this, ms, parameter, resultHandler, boundSql);
            Connection connection = transaction.getConnection();
            Statement stmt = handler.prepare(connection);
            handler.parameterize(stmt);
            return handler.query(stmt,resultHandler);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
