package com.kure.test.base;

import com.kure.test.junit.dao.MockDao;
import com.kure.test.junit.domain.MockDomain;
import com.kure.test.junit.service.MockService;
import com.kure.test.junit.service.MockServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.assertEquals;


public class SimpleDateFormatTest {

    @Test
    public void testSimpleDateFormat(){
        MockDao mockDao = PowerMockito.mock(MockDao.class);
        PowerMockito.when(mockDao.findMock()).thenReturn(new MockDomain());
        MockService mockService = new MockServiceImpl();
        MockDomain mockDomain = mockService.testMock();
        assertEquals(MockDomain.class, mockDomain);
    }
}
