package com.gjw.mapper;

/**
 * @author wei
 */
public interface IUserMapper {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);
}
