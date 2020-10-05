package com.emporium.area.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonalAreaException extends RuntimeException {

  public PersonalAreaException(String msg) {
    super(msg);
  }
}
