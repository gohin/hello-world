package com.kure.test.mybatis;

import com.kure.test.spring.springboot.mail.dao.MailDao;
import com.kure.test.spring.springboot.mail.service.MailService;
import com.kure.test.spring.springboot.mail.vo.MailVo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.TestCase.assertEquals;

/** 
* MyBatisTest Tester. 
* 
* @author <Authors name> 
* @since <pre>3月 9, 2021</pre> 
* @version 1.0 
*/
@RunWith(PowerMockRunner.class)
@PrepareForTest
public class MyBatisTestTest {
    @InjectMocks
    MailService mailService;
    @Mock
    private MailDao mailDao;

@Before
public void before() throws Exception {

}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception {

    MailVo mailVo = new MailVo();
    assertEquals(mailVo, mailService.saveMail(mailVo));

    PowerMockito.when(mailDao.findMailInfo()).thenReturn(10);

    int total = mailService.findMailInfo();
    assertEquals(10, total);
}


} 
