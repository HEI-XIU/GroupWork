package com.example.zuccknowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ZuccKnowledgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuccKnowledgeApplication.class, args);
    }

}
