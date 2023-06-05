package com.gjw.orm.binding;

import com.gjw.orm.mapping.MapperStatement;
import com.gjw.orm.mapping.SqlCommandType;
import com.gjw.orm.session.Configuration;
import com.gjw.orm.session.SqlSession;

import java.lang.reflect.Method;

/**
 * @author wei
 * 映射器方法
 */
public class MapperMethod {


    private final SqlCommand command;

    public MapperMethod(Class<?> mapperInterfaces, Method method, Configuration configuration) {
        this.command = new SqlCommand(configuration, mapperInterfaces, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (command.getType()) {
            case INSERT:
                break;
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.getName(), args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for:" + command.getName());
        }
        return result;
    }

    /**
     * SQL指令
     */
    public static class SqlCommand {

        private final String name;

        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MapperStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }
}
