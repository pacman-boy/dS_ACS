package baseNoStates;

import baseNoStates.areas.Area;
import baseNoStates.areas.Space;

import java.util.ArrayList;

public class UserGroup {
  private final ArrayList<User> users;
  private final String groupName;
  private ArrayList<Area> areas;

  public UserGroup(String name) {
    this.groupName = name;
    users = new ArrayList<User>();
    areas = new ArrayList<Area>();
  }
  public User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    return null;
  }

  public boolean canBeInSpace(Space sp) {
    for (Area area : areas)
    {
      if (area.getSpaces().contains(sp)) {
        return true;
      }
    }
    return false;
  }

  public String getName() {
    return groupName;
  }
  public void addUser(User user) {
    users.add(user);
  }

  public void addArea(Area area) {
    areas.add(area);
  }
}
