package com.wei.orm.datasource.pooled;

import com.wei.orm.datasource.unpooled.UnPooledDataSourceFactory;

import javax.sql.DataSource;

/**
 * @author wei
 * 有连接池的数据源工厂
 */
public class PooledDataSourceFactory extends UnPooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }
}
