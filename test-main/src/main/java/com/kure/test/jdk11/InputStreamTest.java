package com.kure.test.jdk11;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputStreamTest {
    public static void main(String[] args) throws IOException {
        Files.writeString(
                Path.of("./", "tmp.txt"), // 路径
                "hello, jdk11 files api", // 内容
                StandardCharsets.UTF_8); // 编码
        System.out.println(Files.readString(
                Paths.get("./tmp.txt"), // 路径
                StandardCharsets.UTF_8));

        var classLoader = ClassLoader.getSystemClassLoader();
        var inputStream = classLoader.getResourceAsStream("javastack.txt");
        var javastack = File.createTempFile("javastack2", "txt");
        try (var outputStream = new FileOutputStream(javastack)) {
            inputStream.transferTo(outputStream);
        }

    }
}
