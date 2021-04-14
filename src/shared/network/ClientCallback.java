package shared.network;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote
{
  void update(String str) throws RemoteException;

}
