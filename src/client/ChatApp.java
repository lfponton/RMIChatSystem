package client;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class ChatApp extends Application
{
  private ClientFactory clientFactory;

  @Override public void start(Stage stage) throws Exception
  {
    clientFactory = ClientFactory.getInstance();
    ModelFactory.getInstance();
    ViewModelFactory.getInstance();
    ViewHandler.getInstance().start(stage);
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    clientFactory.getClient().unRegisterClient();
  }
}
