package com.emporium.area.service.impl;

import com.emporium.area.exception.export.UserErrorCode;
import com.emporium.area.exception.export.UserException;
import com.emporium.area.model.jpa.User;
import com.emporium.area.repository.UserRepository;
import com.emporium.area.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User findById(String id) {
    return userRepository.findById(id).orElseThrow(() ->
        new UserException(UserErrorCode.USER_NOT_FOUND_EXCEPTION));
  }

  @Override
  public String create(User user) {
    return userRepository.insert(user).getId();
  }

  @Override
  public void update(User user) {
    userRepository.findById(user.getId()).orElseThrow(() ->
        new UserException(UserErrorCode.USER_NOT_FOUND_EXCEPTION));
    userRepository.save(user);
  }

  @Override
  public void delete(String id) {
    userRepository.findById(id).orElseThrow(() ->
        new UserException(UserErrorCode.USER_NOT_FOUND_EXCEPTION));
    userRepository.deleteById(id);
  }
}
