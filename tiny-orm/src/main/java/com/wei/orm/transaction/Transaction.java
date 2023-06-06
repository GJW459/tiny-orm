package com.wei.orm.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wei
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
