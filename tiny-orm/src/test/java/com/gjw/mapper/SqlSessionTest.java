package com.gjw.mapper;

import com.wei.orm.binding.MapperRegistry;
import com.wei.orm.io.Resources;
import com.wei.orm.session.SqlSession;
import com.wei.orm.session.SqlSessionFactory;
import com.wei.orm.session.SqlSessionFactoryBuilder;
import com.wei.orm.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * @author wei
 */
public class SqlSessionTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(SqlSessionTest.class);

    @Test
    public void test_MapperProxyFactory(){

        // 1. 注册MapperRegistry
        MapperRegistry mapperRegistry = new MapperRegistry(null);
        mapperRegistry.addMappers("com.gjw.mapper");
        // 2. 从SqlSession工厂获取SqlSession
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(null);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3. 获取映射对象
        IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);
        // 4. 测试验证
        // String userName = userMapper.queryUserName(10001);
        // LOGGER.info("测试结果：{}",userName);
    }


    @Test
    public void test_SqlSessionFactory() throws IOException {

        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);
        // String userName = userMapper.queryUserName(10001);
        // LOGGER.info("测试结果:{}",userName);
    }





}
