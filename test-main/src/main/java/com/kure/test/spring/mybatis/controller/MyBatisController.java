package com.kure.test.spring.mybatis.controller;

import com.kure.test.spring.mybatis.domain.User;
import com.kure.test.spring.mybatis.service.BookService;
import com.kure.test.spring.mybatis.service.impl.MyBatisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MyBatisController {

    @Autowired
    private MyBatisServiceImpl myBatisService;

    @Autowired
    private BookService bookService;

    @RequestMapping("getUser/{name}")
    public String getUser(@PathVariable String name){
        User user  = myBatisService.testInsertException(name);

        return user.toString();
    }
}
