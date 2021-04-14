package client.network;

import shared.util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject
{
  void startClient();
  void sendMessage(String str);
  void unRegisterClient();
  void setUsername(String str);
  int getNumberOfConnections();
}
