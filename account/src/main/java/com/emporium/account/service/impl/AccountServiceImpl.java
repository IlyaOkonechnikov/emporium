package com.emporium.account.service.impl;

import com.emporium.account.data.jpa.Account;
import com.emporium.account.data.mapper.AccountMapper;
import com.emporium.account.exception.AccountException;
import com.emporium.account.exception.AccountExceptionReason;
import com.emporium.account.repository.AccountRepository;
import com.emporium.account.service.AccountService;
import com.emporium.lib.auth.data.dto.UserDTO;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
  public Account findById(long id) {
    log.debug("findById() - start. id: {}", id);
    Optional<Account> optionalUser = accountRepository.findById(id);
    if (optionalUser.isEmpty()) {
      log.error("An error occurred due to the attempt to find a nonexistent user. id: {}", id);
      throw new AccountException(AccountExceptionReason.ACCOUNT_NOT_FOUND);
    }
    Account account = optionalUser.get();
    log.debug("findById() - end. user: {}", account);
    return account;
  }

  @Override
  public long create(UserDTO dto) {
    log.debug("create() - start. dto: {}", dto);
    Account account = accountMapper.toEntity(dto);
    long id = accountRepository.save(account).getId();
    log.debug("create() - end. id: {}", id);
    return id;
  }

  @Override
  public void update(Account account) {
    log.debug("update() - start. user: {}", account);
    if (accountRepository.findById(account.getId()).isEmpty()) {
      log.error(
          "An error occurred due to the attempt to update a nonexistent user. id: {}",
          account.getId());
      throw new AccountException(AccountExceptionReason.ACCOUNT_NOT_FOUND);
    }
    accountRepository.save(account);
  }

  @Override
  public void delete(long id) {
    log.debug("delete() - start. id: {}", id);
    if (accountRepository.findById(id).isEmpty()) {
      log.error("An error occurred due to the attempt to delete a nonexistent user. id: {}", id);
      throw new AccountException(AccountExceptionReason.ACCOUNT_NOT_FOUND);
    }
    accountRepository.deleteById(id);
  }
}
