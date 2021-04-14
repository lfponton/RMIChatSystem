package client.core;

import client.network.Client;
import client.network.RMIClient;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientFactory
{
  private Client client;
  private static ClientFactory instance;
  private static Lock lock = new ReentrantLock();

  private ClientFactory()
  {
  }

  public static ClientFactory getInstance()
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new ClientFactory();
        }
      }
    }
    return instance;
  }

  public Client getClient()
  {
    if (client == null)
    {
      synchronized (lock)
      {
        if (client == null)
        {
          client = new RMIClient();
        }
      }
    }
    return client;
  }
}
