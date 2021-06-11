package client.views.chat;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shared.transferobjects.Message;

import java.util.ArrayList;

public class ChatViewController implements ViewController
{
  @FXML private TextField messageField;
  @FXML private TextArea messagesArea;
  @FXML private Label numberOfConnections;
  @FXML private ListView<Message> chatBox;
  private ChatViewModel chatViewModel;
  private ViewHandler viewHandler;
  ObservableList<Message> messages;

  public void sendMessageButton(ActionEvent evt)
  {
    chatViewModel.sendMessage();
    messageField.clear();
    messages = FXCollections.observableArrayList(chatViewModel.getMessages());
    chatBox.setItems(messages);
    // TODO: Needs Observer Pattern because it does not update when other client sends message
  }

  public void numberOfConnections(ActionEvent evt) {
    numberOfConnections.setText(String.valueOf(chatViewModel.numberOfConnections()));
  }

  @Override public void init()
  {
    viewHandler = ViewHandler.getInstance();
    chatViewModel = ViewModelFactory.getInstance().getChatViewModel();
    messageField.textProperty().bindBidirectional(chatViewModel.getMessage());


  }
}
