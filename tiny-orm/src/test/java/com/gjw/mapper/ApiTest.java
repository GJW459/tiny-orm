package com.gjw.mapper;

import com.alibaba.fastjson.JSON;
import com.wei.orm.io.Resources;
import com.wei.orm.session.SqlSession;
import com.wei.orm.session.SqlSessionFactory;
import com.wei.orm.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
}
