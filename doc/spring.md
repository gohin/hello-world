## spring 容器描述

加载配置文件->解析->封装beanDefintion对象(beanDefinitionReader)->实例化->完整对象->使用
    
通过抽象类提供的抽象方法，从配置文件 注解等来获取beanDefintion
后置处理 beanFactoryPostProcessor 来装配变量属性
    
bean创建过程
getBean doGetBean createBean createBeanInstance

FactoryBean 用来生产特殊对象，不需要在bean生命周期内产生

bean生命周期  beanFactory

```
* <p>Bean factory implementations should support the standard bean lifecycle interfaces
* as far as possible. The full set of initialization methods and their standard order is:
* <ol>
* <li>BeanNameAware's {@code setBeanName}
* <li>BeanClassLoaderAware's {@code setBeanClassLoader}
* <li>BeanFactoryAware's {@code setBeanFactory}
* <li>EnvironmentAware's {@code setEnvironment}
* <li>EmbeddedValueResolverAware's {@code setEmbeddedValueResolver}
* <li>ResourceLoaderAware's {@code setResourceLoader}
* (only applicable when running in an application context)
* <li>ApplicationEventPublisherAware's {@code setApplicationEventPublisher}
* (only applicable when running in an application context)
* <li>MessageSourceAware's {@code setMessageSource}
* (only applicable when running in an application context)
* <li>ApplicationContextAware's {@code setApplicationContext}
* (only applicable when running in an application context)
* <li>ServletContextAware's {@code setServletContext}
* (only applicable when running in a web application context)
* <li>{@code postProcessBeforeInitialization} methods of BeanPostProcessors
* <li>InitializingBean's {@code afterPropertiesSet}
* <li>a custom init-method definition
* <li>{@code postProcessAfterInitialization} methods of BeanPostProcessors
* </ol>
```

* 1.springboot默认会扫描启动类所在的包及其子包；
* 2.不在自动扫描路径下，需要修改自定义扫描包路径。
* ControllerAdvice注解 搭配ExceptionHandler
* 事务控制
* org.springframework.transaction.interceptor.TransactionAspectSupport
* #invokeWithinTransaction(java.lang.reflect.Method, java.lang.Class, org.springframework.transaction.interceptor.TransactionAspectSupport.InvocationCallback)
* mvc注解解析
* org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#afterPropertiesSet()
* org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#detectHandlerMethods(java.lang.Object)

## spring 循环依赖

三级缓存

 DefaultSingletonBeanRegistry

* singletonFactories
* earlySingletonObjects
* singletonFactories  org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#getEarlyBeanReference(java.lang.Object, java.lang.String)

 * singletonFactories
 * earlySingletonObjects
 * singletonFactories  getBeanEarlyReference
 * 只使用一级缓存行不行？ 不行，可能取到半成品
 * 只使用二级缓存行不行 ？ 行，可以解决循环依赖问题
 * 那三级缓存有什么作用？
 * aop时，有可能会用代理对象替换非代理对象，如果没有三级缓存的话
 * 那么就无法得到代理对象。
 * 三级缓存完成了代理对象替换非代理对象的工作，
 * 确定返回的是唯一的对象
 * 三级缓存是为了解决aop代理产生的循环依赖问题



## spring扩展点 钩子

### BeanDefinition扩展

```
org.springframework.context.annotation.ImportBeanDefinitionRegistrar  -- 手动注册bean 需要配合@Import()

org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry -- 手动注册bean

MapperScannerConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware
-- mybatis 动态注册beanDefination

org.springframework.context.annotation.ConfigurationClassPostProcessor implements BeanDefinitionRegistryPostProcessor

org.mybatis.spring.annotation.MapperScannerRegistrar  implements ImportBeanDefinitionRegistrar  @Import()
```

### BeanFactory扩展点

```
org.springframework.beans.factory.config.BeanFactoryPostProcessor

org.springframework.beans.factory.config.PropertyPlaceholderConfigurer  -- 属性替换 ${...}
```



### Bean实例化中的扩展

```
org.springframework.context.ApplicationContextAware#setApplicationContext -- 获取spring容器钩子

org.springframework.beans.factory.InitializingBean#afterPropertiesSet -- bean初始化方法
```

 ![](C:\Users\Kure\Desktop\5387388-984666daba0ed702.png)

