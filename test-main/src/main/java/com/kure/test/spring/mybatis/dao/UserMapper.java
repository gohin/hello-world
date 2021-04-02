package com.kure.test.spring.mybatis.dao;

import com.kure.test.spring.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectUser();

    int insertUser(User user);
}
