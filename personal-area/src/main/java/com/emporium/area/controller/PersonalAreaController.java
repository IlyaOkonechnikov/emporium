package com.emporium.area.controller;

import com.emporium.area.model.User;
import com.emporium.area.service.UserService;
import com.emporium.lib.auth.UserBasicDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personal-area")
public class PersonalAreaController {

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

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody @Valid UserBasicDTO dto) {
        return userService.create(dto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid User user) {
        userService.update(user);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        userService.delete(id);
    }
}
