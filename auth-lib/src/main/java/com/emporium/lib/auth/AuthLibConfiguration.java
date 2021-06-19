package com.emporium.lib.auth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@ComponentScan
@EnableRedisRepositories(basePackages = "com.emporium.lib.auth.repository")
public class AuthLibConfiguration {}
