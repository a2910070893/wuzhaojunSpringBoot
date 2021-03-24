package com.wuzhaojun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wuzhaojun.mapper")
public class WuzhaojunApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuzhaojunApplication.class, args);
    }

}
