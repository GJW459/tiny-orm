package com.wei.orm.transaction;

import com.wei.orm.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author wei
 */
public interface TransactionFactory {

    Transaction newTransaction(Connection conn);

    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel transactionIsolationLevel,boolean autoCommit);
}
