package com.emporium.area;

import com.emporium.area.repository.AccountRepository;
import com.emporium.area.service.AccountService;
import com.emporium.lib.auth.RegistrationDTO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestContextConfig.class)
public class AccountBasicDTOServiceTest {

  @Autowired
  AccountService accountService;

  @Autowired
  AccountRepository repository;

  private RegistrationDTO dto;
  private Validator validator;

  @Before
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    dto = new RegistrationDTO();
    dto.setUsername("test nickname");
    dto.setEmail("test@gmail.com");
    dto.setPassword("test");
  }

  @Test
  public void createTest() {
//    String id = userService.create(dto);
//    assertEquals(userService.findById(id), dto);
//    userService.delete(id);
  }

  @Test
  public void updateTest() {
//    String id = userService.create(dto);
//    dto.setId(id);
//    String newEmail = "newTest@gmail.com";
//    dto.setEmail(newEmail);
//    userService.update(dto);
//    assertEquals(userService.findById(id).getEmail(), newEmail);
//    userService.delete(id);
  }

  @Test
  public void findByIdFailedTest() {
//    assertException(() -> userService.findById(UUID.randomUUID().toString()));
  }

  @Test
  public void updateFailedTest() {
//    dto.setId(UUID.randomUUID().toString());
//    assertException(() -> userService.update(dto));
  }

  @Test
  public void invalidUsernameTest() {
//    dto.setUsername(UUID.randomUUID().toString());
//    Set<ConstraintViolation<User>> constraintViolations = validator.validate(dto);
//    assertEquals(2, constraintViolations.size());
  }

  @Test
  public void deleteTest() {
    String id = accountService.create(dto);
//    assertEquals(userService.findById(id), dto);
//    userService.delete(id);
//    assertException(() -> userService.delete(id));
  }

  public void assertException(Executable executable) {
//    Exception exception = assertThrows(PersonalAreaException.class, executable);
//    assertEquals(exception.getMessage(), PersonalAreaErrorCode.USER_NOT_FOUND_ERROR.getReason());
  }
}
