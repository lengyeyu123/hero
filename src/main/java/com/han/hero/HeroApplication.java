package com.han.hero;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 不清楚这个注解的作用
@EnableWebSecurity
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.han.hero.project.**.mapper")
public class HeroApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(HeroApplication.class, args);
    }

}
