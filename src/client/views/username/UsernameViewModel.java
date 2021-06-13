package client.views.username;

import client.model.ChatModel;

public class UsernameViewModel
{
  private ChatModel chatModel;

  public UsernameViewModel(ChatModel chatModel) {
    this.chatModel = chatModel;
  }

  public void createUser(String username) {
    chatModel.setUsername(username);
  }
}
