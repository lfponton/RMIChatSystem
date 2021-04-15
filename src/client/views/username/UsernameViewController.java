package client.views.username;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UsernameViewController implements ViewController
{
  @FXML private TextField usernameField;
  private UsernameViewModel usernameViewModel;
  private ViewHandler viewHandler;

  @Override public void init()
  {
    viewHandler = ViewHandler.getInstance();
    usernameViewModel = ViewModelFactory.getInstance().getUsernameViewModel();
    usernameViewModel.createUser(usernameField.getText());
  }

  public void enterUsernameButton(ActionEvent evt)
  {
    usernameViewModel.createUser(usernameField.getText());
    viewHandler.openChatView();
  }

}
