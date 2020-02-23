package com.emporium.area.service;

import com.emporium.area.exception.export.UserErrorCode;
import com.emporium.area.exception.export.UserException;
import com.emporium.area.model.jpa.User;
import com.emporium.area.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User findById(long id) {
    return userRepository.findById(id).orElseThrow(() ->
        new UserException(UserErrorCode.USER_NOT_FOUND_EXCEPTION));
  }

  @Override
  public long create(User user) {
    return userRepository.save(user).getId();
  }

  @Override
  public void update(User user) {
    userRepository.findById(user.getId()).orElseThrow(() ->
        new UserException(UserErrorCode.USER_NOT_FOUND_EXCEPTION));
    userRepository.save(user);
  }

  @Override
  public void delete(long id) {
    userRepository.findById(id).orElseThrow(() ->
        new UserException(UserErrorCode.USER_NOT_FOUND_EXCEPTION));
    userRepository.deleteById(id);
  }
}
