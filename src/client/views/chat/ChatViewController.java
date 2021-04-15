package client.views.chat;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ChatViewController implements ViewController
{
  @FXML private TextField messageField;
  @FXML private TextArea messagesArea;
  @FXML private Label numberOfConnections;
  private ChatViewModel chatViewModel;
  private ViewHandler viewHandler;

  public void sendMessageButton(ActionEvent evt)
  {
    chatViewModel.sendMessage();
  }

  public void numberOfConnections(ActionEvent evt) {
    numberOfConnections.setText(String.valueOf(chatViewModel.numberOfConnections()));
  }

  @Override public void init()
  {
    viewHandler = ViewHandler.getInstance();
    chatViewModel = ViewModelFactory.getInstance().getChatViewModel();
    messageField.textProperty().bindBidirectional(chatViewModel.getMessage());
    messagesArea.textProperty().bind(chatViewModel.getMessages());
  }
}
