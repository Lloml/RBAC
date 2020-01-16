package com.onehammer.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.onehammer.backend.demo.dao","com.onehammer.backend.common.security.dao","com.baomidou.mybatisplus.samples.quickstart.mapper","com.onehammer.backend.sysmanagement.mapper"})
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
