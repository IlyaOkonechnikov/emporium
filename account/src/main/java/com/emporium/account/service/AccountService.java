package com.emporium.account.service;

import com.emporium.account.model.Account;
import com.emporium.lib.auth.UserDTO;

import java.util.List;

public interface AccountService {

  List<Account> findAll();

  Account findById(long id);

  long create(UserDTO dto);

  void update(Account account);

  void delete(long id);
}
