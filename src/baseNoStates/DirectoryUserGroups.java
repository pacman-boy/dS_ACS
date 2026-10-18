package baseNoStates;

import baseNoStates.areas.Area;
import java.util.ArrayList;

public class DirectoryUserGroups {
  private final static ArrayList<UserGroup> userGroups = new ArrayList<UserGroup>();

  public static void makeUsersGroups() { // Creació i assignació d'usuaris a grups i d'areas permitides.
    UserGroup blank = new UserGroup("Blank");
    User bernat = new User("Bernat", "12345");
    bernat.setUserGroup(blank);
    blank.addUser(bernat);
    User blai = new User("Blai", "77532");
    blai.setUserGroup(blank);
    blank.addUser(blai);
    userGroups.add(blank);

    UserGroup administrator = new UserGroup("Administrator");
    User ana = new User("Ana", "11343");
    ana.setUserGroup(administrator);
    administrator.addUser(ana);
    administrator.addArea(DirectoryAreas.findAreaById("building"));
    userGroups.add(administrator);

    UserGroup manager = new UserGroup("Manager");
    User manel = new User("Manel", "95783");
    manel.setUserGroup(manager);
    manager.addUser(manel);
    User marta = new User("Marta", "05827");
    marta.setUserGroup(manager);
    manager.addUser(marta);
    manager.addArea(DirectoryAreas.findAreaById("building"));
    userGroups.add(manager);

    UserGroup employee = new UserGroup("Employee");
    User ernest = new User("ernest", "74984");
    ernest.setUserGroup(employee);
    employee.addUser(ernest);
    User eulalia = new User("eulalia", "43295");
    eulalia.setUserGroup(employee);
    employee.addUser(eulalia);
    employee.addArea(DirectoryAreas.findAreaById("ground_floor"));
    employee.addArea(DirectoryAreas.findAreaById("floor1"));
    employee.addArea(DirectoryAreas.findAreaById("exterior"));
    employee.addArea(DirectoryAreas.findAreaById("stairs"));
    userGroups.add(employee);
  }

  public static User findUserByCredential(String credential) {
    for (UserGroup group : userGroups) {
      User user = group.findUserByCredential(credential);
      if (user != null) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }
}
