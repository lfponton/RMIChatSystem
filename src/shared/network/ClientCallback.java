package shared.network;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote
{
  void newMessage(String str) throws RemoteException;
}
