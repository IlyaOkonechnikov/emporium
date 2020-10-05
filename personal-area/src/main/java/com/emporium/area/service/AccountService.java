package com.emporium.area.service;

import com.emporium.area.model.Account;
import com.emporium.lib.auth.RegistrationDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(ObjectId id);

    String create(RegistrationDTO dto);

    void update(Account account);

    void delete(ObjectId id);
}
