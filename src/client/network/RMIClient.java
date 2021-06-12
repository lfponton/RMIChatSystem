package client.network;

import shared.network.ClientCallback;
import shared.network.RMIServer;
import shared.transferobjects.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIClient implements Client, ClientCallback
{
  private RMIServer server;
  private String username;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public RMIClient() {}

  public void startClient()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer) registry.lookup("Server");
      server.registerClient(this);
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void sendMessage(String str)
  {
    try
    {
      server.sendMessage(new Message(username, str));
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public void unRegisterClient()
  {
      try
      {
        server.unregisterClient(this);
      } catch (RemoteException e) {
        throw new RuntimeException("Could not contact server");
      }
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  @Override public int getNumberOfConnections()
  {
    try
    {
      return server.getNumberOfConnections();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public List<Message> getMessages()
  {
    try
    {
      return server.getMessages();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public void newMessage(List<Message> messages)
  {
    support.firePropertyChange("NewMessage", null, messages);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

}
