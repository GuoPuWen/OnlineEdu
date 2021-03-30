package com.guli.service_cms;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/17 18:39
 */
@SpringBootApplication
@ComponentScan({"com.guli"})
@MapperScan("com.guli.service_cms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
