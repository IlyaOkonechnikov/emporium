package com.emporium.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.emporium.ad.repository")
@SpringBootApplication
public class AdApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdApplication.class, args);
  }
}
