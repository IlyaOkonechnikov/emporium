package com.emporium.account.service.impl;

import com.emporium.account.data.jpa.Account;
import com.emporium.account.data.mapper.AccountMapper;
import com.emporium.account.exception.AccountException;
import com.emporium.account.exception.AccountExceptionReason;
import com.emporium.account.repository.AccountRepository;
import com.emporium.account.service.AccountService;
import com.emporium.lib.auth.data.dto.UserDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountMapper mapper;
  private final AccountRepository repository;

  @Override
  public List<Account> findAll() {
    return repository.findAll();
  }

  @Override
  public Account findById(long id) {
    return findAccountById(id);
  }

  @Override
  public long create(UserDTO dto) {
    return repository.save(mapper.toEntity(dto)).getId();
  }

  @Override
  public void update(Account account) {
    //    todo: correct mapping
    repository.save(findAccountById(account.getId()));
  }

  @Override
  public void delete(long id) {
    findAccountById(id);
    repository.deleteById(id);
  }

  private Account findAccountById(long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new AccountException(AccountExceptionReason.ACCOUNT_NOT_FOUND));
  }
}
