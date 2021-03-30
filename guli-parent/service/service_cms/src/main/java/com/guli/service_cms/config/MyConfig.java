package com.guli.service_cms.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/20 23:57
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.guli.service_cms.mapper")
public class MyConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
