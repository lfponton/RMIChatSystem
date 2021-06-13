package server;

import server.model.ChatDataModelManager;
import server.network.RMIServerImplementation;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    RMIServerImplementation server = new RMIServerImplementation(new ChatDataModelManager());
    server.startServer();
  }

}
