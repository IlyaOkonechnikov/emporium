package com.emporium.area;

import com.emporium.area.repository.UserRepository;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@ComponentScan(basePackages = {
    "com.emporium.area.service",
    "com.emporium.area.repository",
})
public class TestContextConfig {

}
