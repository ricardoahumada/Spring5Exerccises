package com.bananaapps.bananamusic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BananaMusicApplication {
    private static final Logger logger = LoggerFactory.getLogger(BananaMusicApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BananaMusicApplication.class, args);
    }

    @RequestMapping(value = "/song")
    public String song_endpoint() {
        return "Songs endpoint";
    }

    @RequestMapping(value = "/user")
    public String user_endpoint() {
        return "Users endpoint";
    }

    @RequestMapping(value = "/order")
    public String order_endpoint() {
        return "Orders endpoint";
    }

}