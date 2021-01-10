package com.emporium.auth.service;

public interface EmailSenderService {

  void sendConfirmationEmail(long id, String name, String email);
}
