package com.guli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/4 1:47
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.guli"})
@CrossOrigin
public class OssApplication {
    public static void main(String[] args) {

        SpringApplication.run(OssApplication.class, args);
    }
}
