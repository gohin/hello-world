package com.kure.test.spring.springboot.mail.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface MailDao {
    int findMailInfo();
}
