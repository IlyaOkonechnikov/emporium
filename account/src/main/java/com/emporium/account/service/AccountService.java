package com.emporium.account.service;

import com.emporium.account.data.jpa.Account;
import com.emporium.lib.auth.data.dto.UserDTO;
import java.util.List;

public interface AccountService {

  List<Account> findAll();

  Account findById(long id);

  long create(UserDTO dto);

  void update(Account account);

  void delete(long id);
}
