package server.model;

import shared.util.PropertyChangeSubject;

public interface MessageSender extends PropertyChangeSubject
{
  String sendMessage(String message);
}
