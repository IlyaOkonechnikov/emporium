package com.emporium.account.controller;

import com.emporium.account.model.Account;
import com.emporium.account.service.AccountService;
import com.emporium.lib.auth.UserDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

  private final AccountService accountService;

  @GetMapping("/api")
  String api() {
    return "Made it to protected api on resource server!";
  }

  //    just for quick cluster test
  @GetMapping
  public List<Account> findAll() {
    return accountService.findAll();
  }

  @GetMapping("/{id}")
  public Account findById(@PathVariable long id) {
    return accountService.findById(id);
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public long create(@RequestBody @Valid UserDTO dto) {
    return accountService.create(dto);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void update(@RequestBody @Valid Account account) {
    accountService.update(account);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") long id) {
    accountService.delete(id);
  }
}
