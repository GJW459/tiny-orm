package com.wei.orm.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author wei
 */
public interface DataSourceFactory {

    void setProperties(Properties properties);

    DataSource getDataSource();

}
