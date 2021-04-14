package client.core;

import client.model.MessageSender;
import client.model.MessageSenderManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ModelFactory
{
  private MessageSender model;
  private static ModelFactory instance;
  private static Lock lock = new ReentrantLock();

  private ModelFactory()
  {
  }

  public static ModelFactory getInstance()
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new ModelFactory();
        }
      }
    }
    return instance;
  }

  public MessageSender getDataModel() {
    if (model == null) {
      synchronized (lock) {
        if (model == null) {
      model = new MessageSenderManager(ClientFactory.getInstance().getClient());
    }}}
    return model;
  }
}
