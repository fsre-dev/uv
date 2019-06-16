package com.jis.uv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UvApplication {
    public static void main(String[] args) {
        SpringApplication.run(UvApplication.class, args);
    }
}
