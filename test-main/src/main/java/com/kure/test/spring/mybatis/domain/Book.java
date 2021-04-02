package com.kure.test.spring.mybatis.domain;

/**
 * @author Lenovo
 */
public class Book {
    private Integer id;
    private String bookName;

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
