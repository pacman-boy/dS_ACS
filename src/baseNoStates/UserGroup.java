package baseNoStates;

import baseNoStates.areas.Area;

import java.util.ArrayList;

public class UserGroup {
  private final ArrayList<User> users;
  private final String groupName;
  private ArrayList<Area> areas;

  public UserGroup(String name) {
    this.groupName = name;
    users = new ArrayList<User>();
  }
  public User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    return null;
  }

  public String getName() {
    return groupName;
  }
  public void addUser(User user) {
    users.add(user);
  }
}
