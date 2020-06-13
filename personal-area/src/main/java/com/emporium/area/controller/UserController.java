package com.emporium.area.controller;

import com.emporium.area.service.UserService;
import com.emporium.lib.auth.User;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/emporium/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //    just for quick cluster test
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody User user) {
        Preconditions.checkNotNull(user);
        return userService.create(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody User user) {
        Preconditions.checkNotNull(user);
        userService.update(user);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        userService.delete(id);
    }
}
