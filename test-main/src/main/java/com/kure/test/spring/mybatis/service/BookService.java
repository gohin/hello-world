package com.kure.test.spring.mybatis.service;

import com.kure.test.spring.mybatis.domain.Book;

public interface BookService {

    Book testBookInsertException(String name) throws Exception;

    Book testBookInsertException1(String name);
}
