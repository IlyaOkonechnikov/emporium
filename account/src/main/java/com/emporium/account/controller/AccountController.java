package com.emporium.account.controller;

import com.emporium.account.data.jpa.Account;
import com.emporium.account.service.AccountService;
import com.emporium.lib.auth.data.dto.UserDTO;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

  private final AccountService accountService;

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
