package com.gjw.mapper;

/**
 * @author wei
 */
public interface IUserMapper {

    User queryUserById(Integer uId);

    Integer queryUserAge(String uId);
}
