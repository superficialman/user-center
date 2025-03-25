package com.bytebreeze.usercenterboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bytebreeze.usercenterboot.mapper")
public class UserCenterBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterBootApplication.class, args);
    }

}
