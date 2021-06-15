package com.emporium.account;

import com.emporium.lib.auth.AuthLibConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import(AuthLibConfiguration.class)
@EntityScan("com.emporium.account.data.jpa")
@EnableJpaRepositories("com.emporium.account.repository")
public class AccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountApplication.class, args);
  }
}
