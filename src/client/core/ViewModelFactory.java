package client.core;

import client.views.chat.ChatViewModel;
import client.views.username.UsernameViewModel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewModelFactory
{
  private ChatViewModel chatViewModel;
  private UsernameViewModel usernameViewModel;
  private static ViewModelFactory instance;
  private static Lock lock = new ReentrantLock();

  private ViewModelFactory()
  {
    chatViewModel = new ChatViewModel(ModelFactory.getInstance().getDataModel());
    usernameViewModel = new UsernameViewModel(ModelFactory.getInstance().getDataModel());
  }

  public static ViewModelFactory getInstance()
  {
    if (instance == null) {
      synchronized (lock) {
        if (instance == null) {
          instance = new ViewModelFactory();
        }
      }
    }
    return instance;
  }

  public ChatViewModel getChatViewModel() {
    return chatViewModel;
  }

  public UsernameViewModel getUsernameViewModel()
  {
    return usernameViewModel;
  }
}
