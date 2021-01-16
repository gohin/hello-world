package com.kure.test.junit.service;

import com.kure.test.junit.dao.MockDao;
import com.kure.test.junit.domain.MockDomain;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MockServiceImpl implements MockService{

    @Autowired
    private MockDao mockDao;

    @Override
    public MockDomain testMock() {
        MockDomain mockDomain = mockDao.findMock();
        Assert.notNull(mockDomain, "mock is null");
        return mockDomain;
    }
}
