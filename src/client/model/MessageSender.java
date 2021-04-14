package client.model;

import shared.util.PropertyChangeSubject;

public interface MessageSender extends PropertyChangeSubject
{
  void sendMessage(String message);
  void createUser(String username);
  int getNumberOfConnections();
}
