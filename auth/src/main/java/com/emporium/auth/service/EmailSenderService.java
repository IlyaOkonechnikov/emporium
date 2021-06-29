package com.emporium.auth.service;

public interface EmailSenderService {

  void sendConfirmationEmail(String username, String email);
}
