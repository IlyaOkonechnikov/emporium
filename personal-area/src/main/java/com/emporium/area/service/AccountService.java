package com.emporium.area.service;

import com.emporium.area.model.Account;
import com.emporium.lib.auth.UserBasicDTO;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(String id);

    String create(UserBasicDTO dto);

    void update(Account account);

    void delete(String id);
}
