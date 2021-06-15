package client.views.chat;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import shared.transferobjects.Message;

public class ChatViewController implements ViewController
{
  @FXML private TextField messageField;
  @FXML private Label numberOfConnections;
  @FXML private ListView<Message> chatBox;
  private ObservableList<Message> messages;
  private ChatViewModel chatViewModel;
  private ViewHandler viewHandler;


  public void sendMessageButton(ActionEvent evt)
  {
    chatViewModel.sendMessage();
    messageField.clear();
  }

  public void numberOfConnections(ActionEvent evt)
  {
    numberOfConnections
        .setText(String.valueOf(chatViewModel.numberOfConnections()));
  }

  @Override public void init()
  {
    viewHandler = ViewHandler.getInstance();
    chatViewModel = ViewModelFactory.getInstance().getChatViewModel();
    messageField.textProperty().bindBidirectional(chatViewModel.getMessage());
    messages = FXCollections.observableArrayList(chatViewModel.getMessages());
    chatBox.setItems(messages);
    chatViewModel.loadMessages().addListener(
        (ListChangeListener<? super Message>) observable -> onNewMessage(
            observable.getList()));
  }

  private void onNewMessage(ObservableList<? extends Message> list)
  {
    chatBox.setItems((ObservableList<Message>) list);
  }
}
