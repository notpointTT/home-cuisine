package com.hc.commons.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 16:50
 */
@Configuration
@MapperScan("com.hc.**.mapper")
public class MybatisConfig {
}
