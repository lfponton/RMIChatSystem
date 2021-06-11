package shared.network;


import shared.transferobjects.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientCallback extends Remote
{
  void newMessage(List<Message> message) throws RemoteException;
}
