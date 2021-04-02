package com.kure.test.spring.mybatis.service.impl;

import com.kure.test.spring.mybatis.dao.UserMapper;
import com.kure.test.spring.mybatis.domain.User;
import com.kure.test.spring.mybatis.service.BookService;
import com.kure.test.spring.mybatis.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lenovo
 */
@Service
public class MyBatisServiceImpl implements MyBatisService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookService bookService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    /**
     * 非public方法没有事务
     * 内部方法不开启事务
     * 异常捕获 不抛出 事务不生效
     * TransactionAspectSupport#invokeWithinTransaction
     * rollback only
     * serviceA.method(serviceB.method)
     * serviceB异常 serviceA rollback only
     */
    public User testInsertException(String name){
        try {
            try {
                bookService.testBookInsertException(name);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            int i = userMapper.insertUser(new User(name,name + "1",name+"2"));
            if (i > 2) {
                throw new Exception("111");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return userMapper.selectUser().get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User testInsertException1(String name){
        try {
            int i = userMapper.insertUser(new User(name,name + "1",name+"2"));
            if (i > 2) {
                throw new Exception("111");
            }
            userMapper.insertUser(new User(name,name + "E",name+"E"));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return userMapper.selectUser().get(0);
    }


}
