package client.views.chat;

import client.model.ChatModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Message;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class ChatViewModel
{
  private ChatModel chatModel;
  private StringProperty message;
  private String numberOfConnections;
  private ObservableList<Message> messages;


  public ChatViewModel(ChatModel chatModel)
  {
    this.chatModel = chatModel;
    message = new SimpleStringProperty();
    messages = FXCollections.observableArrayList(new ArrayList<>());
    numberOfConnections = "";
    chatModel.addPropertyChangeListener("NewMessage", this::onNewMessage);
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    ObservableList<Message> messageList = FXCollections.observableArrayList((List<Message>)evt.getNewValue());
    Platform.runLater(() -> {
      messages.setAll(messageList);
    });
  }

  public void sendMessage()
  {
    String input = message.get();
    chatModel.sendMessage(input);
  }

  public int numberOfConnections()
  {
    return chatModel.getNumberOfConnections();
  }


  public StringProperty getMessage()
  {
    return message;
  }

  public List<Message> getMessages()
  {
    return chatModel.getMessages();
  }

  public ObservableList<Message> loadMessages() {
    return messages;
  }
}