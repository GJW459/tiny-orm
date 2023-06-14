package com.wei.orm.session.defaults;

import com.wei.orm.executor.Executor;
import com.wei.orm.mapping.Environment;
import com.wei.orm.session.Configuration;
import com.wei.orm.session.SqlSession;
import com.wei.orm.session.SqlSessionFactory;
import com.wei.orm.session.TransactionIsolationLevel;
import com.wei.orm.transaction.Transaction;
import com.wei.orm.transaction.TransactionFactory;

import java.sql.SQLException;

/**
 * @author wei
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment=configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx=transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED,false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            return new DefaultSqlSession(configuration,executor);
        }catch (Exception e){
            try {
                assert tx!=null;
                tx.close();
            }catch (SQLException ignore){

            }
            throw new RuntimeException("Error opening session. Cause:"+e);
        }
    }
}
