package client.model;

import shared.transferobjects.Message;
import shared.util.PropertyChangeSubject;

import java.util.List;

public interface MessageSender extends PropertyChangeSubject
{
  void sendMessage(String message);
  void setUsername(String username);
  int getNumberOfConnections();
  List<Message> getMessages();
}
