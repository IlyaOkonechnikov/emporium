package com.emporium.area.service.impl;

import com.emporium.area.model.Account;
import com.emporium.area.model.mapper.AccountMapper;
import com.emporium.area.repository.AccountRepository;
import com.emporium.area.service.AccountService;
import com.emporium.lib.auth.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountMapper accountMapper;
  private final AccountRepository accountRepository;

  @Override
  public List<Account> findAll() {
    log.debug("findAll() - start.");
    List<Account> accounts = accountRepository.findAll();
    log.debug("findAll() - end. users count: {}", accounts.size());
    return accounts;
  }

  @Override
  public Account findById(ObjectId id) {
    log.debug("findById() - start. id: {}", id);
    Optional<Account> optionalUser = accountRepository.findById(id);
    if (optionalUser.isEmpty()) {
      log.error("An error occurred due to the attempt to find a nonexistent user. id: {}", id);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Account not found.");
    }
    Account account = optionalUser.get();
    log.debug("findById() - end. user: {}", account);
    return account;
  }

  @Override
  public String create(RegistrationDTO dto) {
    log.debug("create() - start. dto: {}", dto);
    Account account = accountMapper.toEntity(dto);
    String id = accountRepository.insert(account).getId().toString();
    log.debug("create() - end. id: {}", id);
    return id;
  }

  @Override
  public void update(Account account) {
    log.debug("update() - start. user: {}", account);
    if (accountRepository.findById(account.getId()).isEmpty()) {
      log.error("An error occurred due to the attempt to update a nonexistent user. id: {}", account.getId());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Account not found.");
    }
    accountRepository.save(account);
  }

  @Override
  public void delete(ObjectId id) {
    log.debug("delete() - start. id: {}", id);
    if (accountRepository.findById(id).isEmpty()) {
      log.error("An error occurred due to the attempt to delete a nonexistent user. id: {}", id);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Account not found.");
    }
    accountRepository.deleteById(id);
  }
}