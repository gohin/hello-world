package com.kure.test.spring.mybatis.dao;

import com.kure.test.spring.mybatis.domain.Book;
import com.kure.test.spring.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<User> selectUser();

    int insertBook(Book book);
}
