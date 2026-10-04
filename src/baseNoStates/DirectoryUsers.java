package baseNoStates;

import java.util.ArrayList;

public final class DirectoryUsers {
  private static final ArrayList<User> users = new ArrayList<>();

  public static void makeUsers() {
    //TODO: make user groups according to the specifications in the comments, because
    // now all are the same

    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later
    User Bernat = new User("Bernat", "12345");
    users.add(Bernat);
    User Blai = new User("Blai", "77532");
    users.add(Blai);

    // employees :
    // Sep. 1 this year to Mar. 1 next year
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    User ernest = new User("ernest", "74984");
    ernest.addArea(DirectoryAreas.findAreaById("ground_floor"));
    ernest.addArea(DirectoryAreas.findAreaById("floor1"));
    ernest.addArea(DirectoryAreas.findAreaById("exterior"));
    ernest.addArea(DirectoryAreas.findAreaById("stairs"));
    users.add(ernest);
    User eulalia = new User("eulalia", "43295");
    eulalia.addArea(DirectoryAreas.findAreaById("ground_floor"));
    ernest.addArea(DirectoryAreas.findAreaById("ground_floor"));
    ernest.addArea(DirectoryAreas.findAreaById("floor1"));
    ernest.addArea(DirectoryAreas.findAreaById("exterior"));
    ernest.addArea(DirectoryAreas.findAreaById("stairs"));
    users.add(eulalia);

    // managers :
    // Sep. 1 this year to Mar. 1 next year
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    User manel = new User("manel", "95783");
    manel.addArea(DirectoryAreas.findAreaById("building"));
    users.add(manel);
    User marta = new User("Marta", "05827");
    manel.addArea(DirectoryAreas.findAreaById("building"));
    users.add(marta);

    // admin :
    // always=Jan. 1 this year to 2100
    // all days of the week
    // all actions
    // all spaces
    User ana = new User("Ana", "11343");
    ana.addArea(DirectoryAreas.findAreaById("building"));
    users.add(ana);
  }

  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }

}
