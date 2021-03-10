package com.kure.test;


import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
// 也可以这样写，写接口的权限定类名
//@Service(interfaceName ="com.md.springboot.service.StudentService",version = "1.0.0",timeout= 15000)
//暴露出接口的类名.class，版本号，
@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public Integer queryAllStudentCount() {
        return 200;
    }
}
