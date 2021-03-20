package com.emporium.lib.auth.data.jpa;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;

@Data
@RedisHash("token")
public class Token {

  private String id;

  @Indexed
  private String value;

  public static Token of(String value) {
    final Token token = new Token();
    token.setValue(value);
    return token;
  }
}
