package server.model;

import shared.transferobjects.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class MessageSenderManager implements MessageSender
{
  private PropertyChangeSupport support;
  private List<Message> messages;


  public MessageSenderManager() {
    support = new PropertyChangeSupport(this);
    messages = new ArrayList<>();
  }

  @Override public void sendMessage(Message message)
  {
    messages.add(message);
    /*
    String result = "";
    for (String m : messages)
    {
      result += m + "\n";
    }

     */

    support.firePropertyChange("NewMessage", null, messages);
  }

  @Override public List<Message> getMessages()
  {
    return new ArrayList<>(messages);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    removePropertyChangeListener(listener);
  }
}
