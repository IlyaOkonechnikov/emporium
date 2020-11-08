package com.emporium.area.controller;

import com.emporium.area.model.Account;
import com.emporium.area.service.AccountService;
import com.emporium.lib.auth.UserDTO;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personal-area")
public class PersonalAreaController {

    private final AccountService accountService;

//    just for quick cluster test
    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable ObjectId id) {
        return accountService.findById(id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody @Valid UserDTO dto) {
        return accountService.create(dto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid Account account) {
        accountService.update(account);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") ObjectId id) {
        accountService.delete(id);
    }
}
