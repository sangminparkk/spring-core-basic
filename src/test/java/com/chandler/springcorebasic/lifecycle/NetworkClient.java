package com.chandler.springcorebasic.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {


    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + this.url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // Service start
    public void connect() {
        System.out.println("connect, url = " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + ", message = " + message);
    }

    public void disconnect() {
        System.out.println("close, url = " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 메세지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
