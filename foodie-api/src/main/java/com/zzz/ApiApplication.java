package com.zzz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhangzhongzhen wrote on 2024/1/23
 * @version 1.0
 * @description:
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zzz.mapper")
@ComponentScan(basePackages = {"com.zzz", "org.n3r.idworker"})
public class ApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
