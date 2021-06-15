package com.emporium.lib.auth.repository;

import com.emporium.lib.auth.data.jpa.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer> {

  Token findByValue(String value);
}
