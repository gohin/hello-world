package com.kure.test.jdk11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8080"))
                .GET()
                .build();
        var client = HttpClient.newHttpClient();
        // 同步
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        // 异步
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
    }
}
