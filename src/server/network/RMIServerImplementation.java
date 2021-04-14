package server.network;

import server.model.MessageSender;
import server.model.MessageSenderManager;
import shared.network.ClientCallback;
import shared.network.RMIServer;
import shared.transferobjects.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class RMIServerImplementation implements RMIServer
{
  private MessageSender messageSender;
  private Map<ClientCallback, PropertyChangeListener> listeners = new HashMap<>();

  public RMIServerImplementation(MessageSender messageSender) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.messageSender = messageSender;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Server", this);
  }

  @Override public String sendMessage(Message msg)
  {
    return messageSender.sendMessage(msg.toString());
  }

  @Override public void registerClient(ClientCallback clientCallback)
  {
    PropertyChangeListener listener = new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        try {
          clientCallback.update((String) evt.getNewValue());
        } catch (RemoteException e) {
          e.printStackTrace();
          messageSender.removePropertyChangeListener("NewMessage", this);
        }
      }
    };
    listeners.put(clientCallback, listener);
    messageSender.addPropertyChangeListener("NewMessage", listener);
  }

  @Override public void unregisterClient(ClientCallback clientCallback)
  {
    PropertyChangeListener listener = listeners.get(clientCallback);
    if (listener != null) {
      listeners.remove(clientCallback, listener);
      messageSender.removePropertyChangeListener("NewMessage", listener);
    }
  }

  @Override public int getNumberOfConnections()
  {
    return listeners.size();
  }

}