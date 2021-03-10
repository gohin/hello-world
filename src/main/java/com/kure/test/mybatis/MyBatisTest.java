package com.kure.test.mybatis;

import com.kure.test.mybatis.dao.BlogMapper;
import com.kure.test.mybatis.domain.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    public static void main(String[] args) throws IOException {
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()){
            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            List<Blog> blogs = blogMapper.selectBlog();
            blogs.forEach(blog -> {
                System.out.println(blog.toString());
            });
        }
    }
}
