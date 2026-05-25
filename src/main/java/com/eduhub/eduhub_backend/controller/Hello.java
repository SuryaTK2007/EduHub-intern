package com.eduhub.eduhub_backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @Value("${spring.application.name}")
    private String appName;

    private final Environment environment;

    public Hello(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/env")
    public String getEnv() {
        String port = environment.getProperty("server.port", "8080");
        return "App Name : " + appName + " Port : " + port;
    }
}