package com.emporium.area.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.emporium.area.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

  private final String host;
  private final Integer port;
  private final String username;
  private final String password;
  private final String database;

  public MongoConfig(@Value("${spring.data.mongodb.host}") String host,
                     @Value("${spring.data.mongodb.port}") Integer port,
                     @Value("${spring.data.mongodb.username}") String username,
                     @Value("${spring.data.mongodb.password}") String password,
                     @Value("${spring.data.mongodb.database}") String database) {
    this.host = host;
    this.port = port;
    this.username = username;
    this.password = password;
    this.database = database;
  }

  @Override
  protected String getDatabaseName() {
    return database;
  }

  @Bean
  @Override
  public MongoClient mongoClient() {
    MongoClientSettings clientSettings = MongoClientSettings.builder()
        .credential(MongoCredential.createCredential(username, database, password.toCharArray()))
        .applyToClusterSettings(settings -> settings.hosts(Collections.singletonList(new ServerAddress(host, port))))
        .build();
    return MongoClients.create(clientSettings);
  }
}
