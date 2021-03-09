package com.kure.test.mybatis.dao;

import com.kure.test.mybatis.domain.Blog;

import java.util.List;

public interface BlogMapper {
    List<Blog> selectBlog();
}
