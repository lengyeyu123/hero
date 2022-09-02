package com.han.hero;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.han.hero.project.**.mapper")
public class HeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroApplication.class, args);
    }

}