package client.views.chat;

import client.model.MessageSender;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ChatViewModel
{
  private MessageSender messageSender;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private StringProperty message;
  private String numberOfConnections;
  private ObservableList<Message> messages;


  public ChatViewModel(MessageSender messageSender)
  {
    this.messageSender = messageSender;
    message = new SimpleStringProperty();
    messages = FXCollections.observableArrayList(new ArrayList<>());
    numberOfConnections = "";
    messageSender.addPropertyChangeListener("NewMessage", this::onNewMessage);
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
    messageSender.sendMessage(input);
  }

  public int numberOfConnections()
  {
    return messageSender.getNumberOfConnections();
  }


  public StringProperty getMessage()
  {
    return message;
  }

  public List<Message> getMessages()
  {
    return messages;
  }

  public ObservableList<Message> loadMessages() {
    return messages;
  }
}