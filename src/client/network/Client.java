package client.network;

import shared.transferobjects.Message;
import shared.util.PropertyChangeSubject;

import java.util.List;

public interface Client extends PropertyChangeSubject
{
  void startClient();
  void sendMessage(String str);
  void unRegisterClient();
  void setUsername(String str);
  int getNumberOfConnections();
  List<Message> getMessages();
}
