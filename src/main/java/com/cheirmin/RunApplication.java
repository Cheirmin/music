package com.cheirmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Message:
 * @Author：Cheirmin
 * @Date: 2020/3/2 23:04
 * @Version 1.0
 */
@MapperScan("com.cheirmin.pojo")
@SpringBootApplication(scanBasePackages = {"com.cheirmin"})
public class RunApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class,args);
    }
}
