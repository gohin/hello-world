package com.kure.test.spring.mybatis.service.impl;

import com.kure.test.spring.mybatis.dao.BookMapper;
import com.kure.test.spring.mybatis.domain.Book;
import com.kure.test.spring.mybatis.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Book testBookInsertException(String name) {
        bookMapper.insertBook(new Book(name));
        throw new RuntimeException("1111");
    }

    @Override
    public Book testBookInsertException1(String name) {
        return null;
    }
}
