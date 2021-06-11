package shared.network;

import shared.transferobjects.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIServer extends Remote
{
  void sendMessage(Message msg) throws RemoteException;
  void registerClient(ClientCallback clientCallback) throws RemoteException;
  void unregisterClient(ClientCallback clientCallback) throws RemoteException;
  int getNumberOfConnections() throws RemoteException;
  List<Message> getMessages() throws RemoteException;
}
