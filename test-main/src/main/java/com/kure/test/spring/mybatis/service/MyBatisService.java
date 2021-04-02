package com.kure.test.spring.mybatis.service;

import com.kure.test.spring.mybatis.domain.User;

public interface MyBatisService {

    User testInsertException(String name);

    User testInsertException1(String name);
}
