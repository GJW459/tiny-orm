package com.wei.orm.transaction.jdbc;

import com.wei.orm.session.TransactionIsolationLevel;
import com.wei.orm.transaction.Transaction;
import com.wei.orm.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author wei
 */
public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel transactionIsolationLevel, boolean autoCommit) {
        return new JdbcTransaction(dataSource,transactionIsolationLevel,autoCommit);
    }
}
