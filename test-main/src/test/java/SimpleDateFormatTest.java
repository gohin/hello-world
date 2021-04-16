//import com.kure.test.junit.dao.MockDao;
//import com.kure.test.junit.domain.MockDomain;
//import com.kure.test.junit.service.MockServiceImpl;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import static org.mockito.ArgumentMatchers.eq;
//
//
///**
// * Powermockito.mock() 方法主要是根据class创建一个对应的mock对象
// * @RunWith 就是显式的告诉Junit使用某个指定的Runner来运行Test Case
// *
// */
//@RunWith(PowerMockRunner.class)
///**
// * 当使用PowerMockito.whenNew方法时，必须加注解@PrepareForTest和@RunWith。注解@PrepareForTest里写的类是需要mock的new对象代码所在的类。
// *
// * 当需要mock final方法的时候，必须加注解@PrepareForTest和@RunWith。注解@PrepareForTest里写的类是final方法所在的类。 
// *
// * 当需要mock静态方法的时候，必须加注解@PrepareForTest和@RunWith。注解@PrepareForTest里写的类是静态方法所在的类。
// *
// * 当需要mock私有方法的时候, 只是需要加注解@PrepareForTest，注解里写的类是私有方法所在的类
// *
// * 当需要mock系统类的静态方法的时候，必须加注解@PrepareForTest和@RunWith。注解里写的类是需要调用系统方法所在的类
// */
//@PrepareForTest
//public class SimpleDateFormatTest {
//
//    @InjectMocks //构建实例对象 不能为接口
//    MockServiceImpl mockService;
//
//    @Mock
//    MockDao mockDao;
//
//    @Before
//    public void setUp(){
//        // 等同于@AutoWired
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testSimpleDateFormat(){
//        PowerMockito.when(mockDao.findMock()).thenReturn(new MockDomain());
//        MockDomain mockDomain = mockService.testMock();
//
//
//        // 构建返回结果对象
//        PowerMockito.when(mockDao.findMock()).thenAnswer(invocation -> {
//            MockDomain mockDomain1 = new MockDomain();
//            mockDomain1.setMock1("1111");
//            return mockDomain1;
//        });
//
//        // 验证方法是否被调用
////        Mockito.verify()
//        // 断言
//        Assert.assertEquals(MockDomain.class, mockDomain);
//
//        Mockito.any(String.class); // 构建参数
//        // Mockito.verify 判断方法是否有调用
//
//        Mockito.verify(mockService, Mockito.never()).testMockParam(Mockito.anyString(), eq("dailyDeliveryProfit"), Mockito.anyString(), eq(null));
//
//    }
//}
