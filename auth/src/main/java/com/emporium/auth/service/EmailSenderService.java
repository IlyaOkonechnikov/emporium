package com.emporium.auth.service;

import org.bson.types.ObjectId;

public interface EmailSenderService {

  void sendConfirmationEmail(ObjectId id, String name, String email);
}
