package client.core;

import client.model.ChatModel;
import client.model.ChatModelManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ModelFactory
{
  private ChatModel model;
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

  public ChatModel getDataModel() {
    if (model == null) {
      synchronized (lock) {
        if (model == null) {
      model = new ChatModelManager(ClientFactory.getInstance().getClient());
    }}}
    return model;
  }
}
