package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DirectoryUserGroups {
  private static final Logger logger = LoggerFactory.getLogger(DirectoryUserGroups.class);
  // Patro Singleton: catalogo unico de grupos de usuarios y sus permisos
  private static final DirectoryUserGroups UNIQUE_INSTANCE = new DirectoryUserGroups();
  private final ArrayList<UserGroup> userGroups = new ArrayList<>();

  private DirectoryUserGroups() {
    makeUsersGroups();
  }

  // Punto de acceso global al Singleton
  public static DirectoryUserGroups getInstance() {
    return UNIQUE_INSTANCE;
  }

  private void makeUsersGroups() { // Creació i assignació d'usuaris a grups i d'areas permitides.
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
    administrator.addArea(DirectoryAreas.getInstance().findAreaById("building"));
    administrator.addAction(Actions.OPEN);
    administrator.addAction(Actions.CLOSE);
    administrator.addAction(Actions.LOCK);
    administrator.addAction(Actions.UNLOCK);
    administrator.addAction(Actions.UNLOCK_SHORTLY);
    // No es necesario añadir un horario ya que tienen acceso siempre.
    userGroups.add(administrator);

    UserGroup manager = new UserGroup("Manager");
    User manel = new User("Manel", "95783");
    manel.setUserGroup(manager);
    manager.addUser(manel);
    User marta = new User("Marta", "05827");
    marta.setUserGroup(manager);
    manager.addUser(marta);
    manager.addArea(DirectoryAreas.getInstance().findAreaById("building"));
    manager.addAction(Actions.OPEN);
    manager.addAction(Actions.CLOSE);
    manager.addAction(Actions.LOCK);
    manager.addAction(Actions.UNLOCK);
    manager.addAction(Actions.UNLOCK_SHORTLY);

    ArrayList<DayOfWeek> daysManager = new ArrayList<>(List.of(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY
    ));

    Schedule managerSchedule = new Schedule(LocalDate.of(2026, 9, 1), LocalDate.of(2027, 3, 1),
        daysManager, LocalTime.of(8, 0), LocalTime.of(20, 0));
    manager.addSchedule(managerSchedule);
    userGroups.add(manager);

    UserGroup employee = new UserGroup("Employee");
    User ernest = new User("Ernest", "74984");
    ernest.setUserGroup(employee);
    employee.addUser(ernest);
    User eulalia = new User("Eulalia", "43295");
    eulalia.setUserGroup(employee);
    employee.addUser(eulalia);
    employee.addArea(DirectoryAreas.getInstance().findAreaById("ground_floor"));
    employee.addArea(DirectoryAreas.getInstance().findAreaById("floor1"));
    employee.addArea(DirectoryAreas.getInstance().findAreaById("exterior"));
    employee.addArea(DirectoryAreas.getInstance().findAreaById("stairs"));
    employee.addAction(Actions.OPEN);
    employee.addAction(Actions.CLOSE);
    employee.addAction(Actions.UNLOCK_SHORTLY);

    ArrayList<DayOfWeek> daysEmployee = new ArrayList<>(List.of(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY
    ));

    Schedule employeeSchedule = new Schedule(LocalDate.of(2026, 9, 1), LocalDate.of(2027, 3, 1),
        daysEmployee, LocalTime.of(9, 0), LocalTime.of(17, 0));
    employee.addSchedule(employeeSchedule);
    userGroups.add(employee);
  }

  public User findUserByCredential(String credential) {
    for (UserGroup group : userGroups) {
      User user = group.findUserByCredential(credential);
      if (user != null) {
        return user;
      }
    }
    logger.warn("User with credential {} not found", credential);
    return null; // otherwise we get a Java error
  }
}
