package com.wei.orm.datasource.pooled;

import com.wei.orm.datasource.unpooled.UnPooledDataSourceFactory;

import javax.sql.DataSource;

/**
 * @author wei
 * 有连接池的数据源工厂
 */
public class PooledDataSourceFactory extends UnPooledDataSourceFactory {

    @Override
    public DataSource getDataSource() {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver(properties.getProperty("driver"));
        pooledDataSource.setUrl(properties.getProperty("url"));
        pooledDataSource.setUsername(properties.getProperty("username"));
        pooledDataSource.setPassword(properties.getProperty("password"));
        return pooledDataSource;
    }
}
