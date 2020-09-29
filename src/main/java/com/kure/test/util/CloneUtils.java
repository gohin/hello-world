package com.kure.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @version 1.0
 * @ClassName CloneUtils
 * @Description 对象拷贝工具
 * @since 2018/6/17 14:02
 **/
public final class CloneUtils {
    private static final Logger logger = LoggerFactory.getLogger(CloneUtils.class);

    private CloneUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 深拷贝
     *
     * @param obj
     * @return <T> T
     * <p>Update:Versions|Content|Name|Date</p>
     * @author TANGYE
     * @since 2018-1-4下午7:55:45
     */
    public static <T> T clone(T obj) {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        T objClone = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            objClone = (T) ois.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            SdpUtils.close(oos);
            SdpUtils.close(ois);
        }
        return objClone;
    }

}
