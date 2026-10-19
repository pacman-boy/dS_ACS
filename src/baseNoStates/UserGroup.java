package baseNoStates;

import baseNoStates.areas.Area;
import baseNoStates.areas.Space;

import java.time.LocalDateTime;
import java.util.ArrayList;
// Centralitza els permisos d'accés d'un rol (àrees permeses, accions i horari Schedule)
// per compartir-los entre tots els usuaris del mateix grup sense duplicar informació a cada User.
public class UserGroup {
  private final ArrayList<User> users;
  private final String groupName;
  private ArrayList<Area> areas;
  private ArrayList<String> actions;
  private Schedule schedule;

  public UserGroup(String name) {
    this.groupName = name;
    users = new ArrayList<User>();
    areas = new ArrayList<Area>();
    actions = new ArrayList<String>();
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
    for (Area area : areas) {
      if (area.getSpaces().contains(sp)) {
        return true;
      }
    }
    return false;
  }

  public boolean canDoAction(String action) {
    return actions.contains(action);
  }

  public boolean canDoActionNow(LocalDateTime now) {
    if (schedule != null) {
      if (schedule.canSendRequests(now)) {
        return true;
      }
      return false;
    }
    return true;
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

  public void addAction(String action) {
    actions.add(action);
  }

  public void addSchedule(Schedule schedule) {
    this.schedule = schedule;
  }
}
