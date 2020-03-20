package com.english;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.info("RUN");
        SpringApplication.run(Application.class, args);
    }
}
