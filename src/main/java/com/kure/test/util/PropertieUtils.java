/**  
 * @Title: BaseSdsManagerService.java
 * @Package com.erayt.xfunds.manager.sdsImpl
 * @Description: 
 * @author liping
 * @date 2015-12-23
 */
package com.kure.test.util;

import com.erayt.xfunds.sdp.base.father.tranflow.spring.PropertyPlaceholder;

import java.util.Properties;

/**
 * @Title: PropertieUtils
 * @Description: propertie 工具类
 * @Author: Jiangdong.Yi
 * @Version: 1.1.0
 * @created 2016-2-19 下午04:49:29
 */
public final class PropertieUtils {
    private static final PropertieUtils propertieUtils = new PropertieUtils();
    private PropertyPlaceholder configure;
    
    public static PropertieUtils getInstance() {
        return propertieUtils;
    }
    
    public PropertieUtils(){
    }
    
    public  String getProperty(String key){
        if(configure == null){
            return null;
        }
        return configure.getProperty(key);
    }

    public Properties getProperties(){
        return configure.getProperties();
    }

    public  void setConfigure(PropertyPlaceholder configure) {
        this.configure = configure;
    }
    
}
