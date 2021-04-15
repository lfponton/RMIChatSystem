package client.core;

import client.views.chat.ChatViewController;
import client.views.username.UsernameViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewHandler
{
  private Stage stage;
  private Scene chatScene;
  private Scene userScene;
  private ViewModelFactory viewModelFactory;
  private static ViewHandler instance;
  private static Lock lock = new ReentrantLock();

  private ViewHandler()
  {
    this.viewModelFactory = ViewModelFactory.getInstance();
  }

  public static ViewHandler getInstance()
  {
    if (instance == null)
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new ViewHandler();
        }
      }
    return instance;
  }

  public void start(Stage stage)
  {
    this.stage = stage;
    openUserView();
    stage.show();
  }

  private void openUserView()
  {
    FXMLLoader loader = new FXMLLoader();
    if (userScene == null)
    {
      Parent root = getRootByPath("../views/username/UsernameView.fxml", loader);
      UsernameViewController controller = loader.getController();
      controller.init();
      userScene = new Scene(root);
    }
    stage.setTitle("Username");
    stage.setScene(userScene);
  }

  public void openChatView()
  {
    FXMLLoader loader = new FXMLLoader();
    if (chatScene == null)
    {
      Parent root = getRootByPath("../views/chat/ChatView.fxml", loader);
      ChatViewController controller = loader.getController();
      controller.init();
      chatScene = new Scene(root);
    }
    stage.setTitle("Chat");
    stage.setScene(chatScene);
  }

  private Parent getRootByPath(String path, FXMLLoader loader)
  {
    loader.setLocation(getClass().getResource(path));
    Parent root = null;
    try
    {
      root = loader.load();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return root;
  }
}
