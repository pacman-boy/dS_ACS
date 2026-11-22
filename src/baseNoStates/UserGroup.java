package baseNoStates;

import baseNoStates.areas.Area;
import baseNoStates.areas.Space;
import baseNoStates.visitor.GetSpacesVisitor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Centralitza els permisos d'accés d'un rol (àrees permeses, accions i horari Schedule)
// per compartir-los entre tots els usuaris del mateix grup sense duplicar informació a cada User.
public class UserGroup {
  private static final Logger logger = LoggerFactory.getLogger(UserGroup.class);
  private final ArrayList<User> users;
  private final String groupName;
  private final ArrayList<Area> areas;
  private final ArrayList<String> actions;
  private Schedule schedule;

  public UserGroup(String name) {
    this.groupName = name;
    users = new ArrayList<>();
    areas = new ArrayList<>();
    actions = new ArrayList<>();
  }

  public User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    return null;
  }

  // Patro Visitor: obtiene todos los Space del area para comprobar si sp esta permitido
  public boolean canBeInSpace(Space sp) {
    for (Area area : areas) {
      GetSpacesVisitor visitor = new GetSpacesVisitor();
      area.acceptVisitor(visitor);
      if (visitor.getSpaces().contains(sp)) {
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
      return schedule.canSendRequests(now);
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
