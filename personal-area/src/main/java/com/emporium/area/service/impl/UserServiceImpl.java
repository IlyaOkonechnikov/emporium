package com.emporium.area.service.impl;

import com.emporium.area.exception.PersonalAreaErrorCode;
import com.emporium.area.exception.PersonalAreaException;
import com.emporium.area.repository.UserRepository;
import com.emporium.area.service.UserService;

import com.emporium.area.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public List<User> findAll() {
    log.debug("findAll() - start.");
    List<User> users = userRepository.findAll();
    log.debug("findAll() - end. users count: {}", users.size());
    return users;
  }

  @Override
  public User findById(String id) {
    log.debug("findById() - start. id: {}", id);
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isEmpty()) {
      log.error("An error occurred due to the attempt to find a nonexistent user. id: {}", id);
      throw new PersonalAreaException(PersonalAreaErrorCode.USER_NOT_FOUND_ERROR);
    }
    User user = optionalUser.get();
    log.debug("findById() - end. user: {}", user);
    return user;
  }

  @Override
  public String create(User user) {
    log.debug("create() - start. user: {}", user);
    String id = userRepository.insert(user).getId();
    log.debug("create() - end. id: {}", id);
    return id;
  }

  @Override
  public void update(User user) {
    log.debug("update() - start. user: {}", user);
    if (userRepository.findById(user.getId()).isEmpty()) {
      log.error("An error occurred due to the attempt to update a nonexistent user. id: {}", user.getId());
      throw new PersonalAreaException(PersonalAreaErrorCode.USER_NOT_FOUND_ERROR);
    }
    userRepository.save(user);
  }

  @Override
  public void delete(String id) {
    log.debug("delete() - start. id: {}", id);
    if (userRepository.findById(id).isEmpty()) {
      log.error("An error occurred due to the attempt to delete a nonexistent user. id: {}", id);
      throw new PersonalAreaException(PersonalAreaErrorCode.USER_NOT_FOUND_ERROR);
    }
    userRepository.deleteById(id);
  }
}