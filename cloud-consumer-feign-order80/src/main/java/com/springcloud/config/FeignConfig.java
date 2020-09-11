package com.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign日志打印功能
 * 1.配置日志Bean
 * 2.YML文件中需开启日志的Feign客户端
 *
 * 日志级别：
 * NONE：默认的，不显示任何日志
 * BASIC：仅记录请求方式、URL、响应状态码及执行时间
 * HEADERS：除了BASIC中定义的信息之外，还有请求和响应的头信息
 * FULL：除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据
 */
@Configuration
public class FeignConfig {
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
