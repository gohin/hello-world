package com.kure.test.junit.service;

import com.kure.test.junit.domain.MockDomain;
import org.springframework.stereotype.Repository;

@Repository
public interface MockService {
    MockDomain testMock();

    MockDomain testMockParam(String s1, String s2, String s3, String s4);
}
