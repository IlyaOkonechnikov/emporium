package com.emporium.account.service;

import com.emporium.account.model.Account;
import com.emporium.lib.auth.UserDTO;

import org.bson.types.ObjectId;

import java.util.List;

public interface AccountService {

  List<Account> findAll();

  Account findById(ObjectId id);

  String create(UserDTO dto);

  void update(Account account);

  void delete(ObjectId id);
}
