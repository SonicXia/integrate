package com.atsonic.integrate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atsonic.integrate.mapper")
public class IntegrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrateApplication.class, args);
    }

}
