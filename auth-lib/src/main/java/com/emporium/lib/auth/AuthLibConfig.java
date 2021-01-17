package com.emporium.lib.auth;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EntityScan("com.emporium.lib.auth.data.jpa")
@EnableJpaRepositories("com.emporium.lib.auth.repository")
public class AuthLibConfig {
}
