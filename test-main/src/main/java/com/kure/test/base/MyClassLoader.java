package com.kure.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.security.SecureClassLoader;

public class MyClassLoader  extends SecureClassLoader {
    private String classPath;
    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{

        // 修改class后缀 修改 myclass
        String filePath = this.classPath + name.replaceAll(".","\\").concat(".myclass");
        try(FileInputStream fileInputStream = new FileInputStream(new File(filePath))){
            byte[] bytes = new byte[1024];
            int length;
//            while ((length = fileInputStream.read()) != -1) {
//                // ba.write(length);
//
//
//            }
            bytes = fileInputStream.readAllBytes();
            return super.defineClass(name, bytes,0, bytes.length);
        } catch (Exception e) {
            throw new ClassNotFoundException("未找到类");
        }
    }
}
