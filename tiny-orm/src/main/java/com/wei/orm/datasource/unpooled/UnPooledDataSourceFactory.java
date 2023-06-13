package com.wei.orm.datasource.unpooled;

import com.wei.orm.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author wei
 */
public class UnPooledDataSourceFactory implements DataSourceFactory {

    protected Properties properties;

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        UnPooledDataSource unPooledDataSource = new UnPooledDataSource();
        unPooledDataSource.setDriver(properties.getProperty("driver"));
        unPooledDataSource.setUrl(properties.getProperty("url"));
        unPooledDataSource.setUsername(properties.getProperty("username"));
        unPooledDataSource.setPassword(properties.getProperty("password"));
        return unPooledDataSource;
    }
}
