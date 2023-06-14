package com.gjw.mapper;

import com.alibaba.fastjson.JSON;
import com.wei.orm.datasource.pooled.PooledDataSource;
import com.wei.orm.io.Resources;
import com.wei.orm.session.SqlSession;
import com.wei.orm.session.SqlSessionFactory;
import com.wei.orm.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wei
 */
public class ApiTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_select_one() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);
        User user = userMapper.queryUserById(1);
        LOGGER.info(JSON.toJSONString(user));
    }

    @Test
    public void test_un_pool_datasource() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);
        for (int i = 0; i < 50; i++) {
            User user = userMapper.queryUserById(1);
            LOGGER.info("测试结果:{}", JSON.toJSONString(user));
        }
    }

    @Test
    public void test_pool_datasource() throws Exception {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.cj.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/study?useSSL=false&&serverTimezone=UTC");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("123456");
        // 持续获取连接
        while (true){
            Connection connection = pooledDataSource.getConnection();
            System.out.println(connection);
            Thread.sleep(1000);
            connection.close();
        }
    }

    @Test
    public void test_executor() throws IOException {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        User user = mapper.queryUserById(1);
        LOGGER.info("测试结果:{}",JSON.toJSONString(user));
    }

}
