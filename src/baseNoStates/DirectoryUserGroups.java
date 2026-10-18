package baseNoStates;

import java.util.ArrayList;

public class DirectoryUserGroups {
  private static ArrayList<UserGroup> userGroups;

  public DirectoryUserGroups() {
    userGroups = new ArrayList<UserGroup>();
  }

  public static void makeUsersGroups() {
    UserGroup Blank = new UserGroup("Blank");
    Blank.addUser(new User("Bernat", "12345"));
    Blank.addUser(new User("Blai", "77532"));
    userGroups.add(Blank);

    UserGroup Administrator = new UserGroup("Administrator");
    userGroups.add(Administrator);

    UserGroup Manager = new UserGroup("Manager");
    userGroups.add(Manager);

    UserGroup Employee = new UserGroup("Employee");
    userGroups.add(Employee);
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

