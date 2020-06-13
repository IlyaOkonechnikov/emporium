package com.emporium.area;

import com.emporium.area.exception.UserErrorCode;
import com.emporium.area.exception.UserException;
import com.emporium.area.repository.UserRepository;
import com.emporium.area.service.UserService;

import com.emporium.lib.auth.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestContextConfig.class)
public class UserServiceTest {

  @Autowired
  UserService userService;

  @Autowired
  UserRepository repository;

  private User user;
  private Validator validator;

  @Before
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    user = new User();
    user.setUsername("test nickname");
    user.setEmail("test@gmail.com");
    user.setName("test name");
    user.setSurname("test surname");
    user.setPatronymic("test patronymic");
  }

  @Test
  public void createTest() {
    String id = userService.create(user);
    assertEquals(userService.findById(id), user);
    userService.delete(id);
  }

  @Test
  public void updateTest() {
    String id = userService.create(user);
    user.setId(id);
    String newEmail = "newTest@gmail.com";
    user.setEmail(newEmail);
    userService.update(user);
    assertEquals(userService.findById(id).getEmail(), newEmail);
    userService.delete(id);
  }

  @Test
  public void findByIdFailedTest() {
    assertException(() -> userService.findById(UUID.randomUUID().toString()));
  }

  @Test
  public void updateFailedTest() {
    user.setId(UUID.randomUUID().toString());
    assertException(() -> userService.update(user));
  }

  @Test
  public void invalidUsernameTest() {
    user.setUsername(UUID.randomUUID().toString());
    Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
    assertEquals(2, constraintViolations.size());
  }

  @Test
  public void deleteTest() {
    String id = userService.create(user);
    assertEquals(userService.findById(id), user);
    userService.delete(id);
    assertException(() -> userService.delete(id));
  }

  public void assertException(Executable executable) {
    Exception exception = assertThrows(UserException.class, executable);
    assertEquals(exception.getMessage(), UserErrorCode.USER_NOT_FOUND_ERROR.getReason());
  }
}
