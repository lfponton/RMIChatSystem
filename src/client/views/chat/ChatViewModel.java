package client.views.chat;

import client.model.MessageSender;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import shared.transferobjects.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ChatViewModel
{
  private MessageSender messageSender;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private StringProperty message;
  private List<Message> messages;
  private String numberOfConnections;

  public ChatViewModel(MessageSender messageSender)
  {
    this.messageSender = messageSender;
    message = new SimpleStringProperty();
    numberOfConnections = "";
    messageSender.addPropertyChangeListener("NewMessage", this::onNewMessage);
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {

    messages = (List<Message>) evt.getNewValue();
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

  public void loadMessages() {
    List<Message> messageList = messageSender.getMessages();
  }
}