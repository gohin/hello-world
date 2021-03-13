package com.kure.test;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
    // 调用暴露的接口
    @Reference(interfaceClass = StudentService.class, check = false)
    private StudentService studentService;

    @GetMapping(value = "/student/count")
    @ResponseBody
    public Object studentCount(){
        Integer count = studentService.queryAllStudentCount();
        return "学生的总人数："+count;
    }
}
