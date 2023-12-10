package com.xxx.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 16:50
 */
@Configuration
@MapperScan("com.xxx.**.mapper")
public class MybatisConfig {
}
