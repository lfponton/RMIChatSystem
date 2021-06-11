package server.model;

import shared.transferobjects.Message;
import shared.util.PropertyChangeSubject;

import java.util.List;

public interface MessageSender extends PropertyChangeSubject
{
  void sendMessage(Message message);
  List<Message> getMessages();
}
