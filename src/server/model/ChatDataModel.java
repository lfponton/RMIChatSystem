package server.model;

import shared.transferobjects.Message;
import shared.util.PropertyChangeSubject;

import java.util.List;

public interface ChatDataModel extends PropertyChangeSubject
{
  void sendMessage(Message message);
  List<Message> getMessages();
}
