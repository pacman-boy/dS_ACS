package baseNoStates;

import baseNoStates.areas.Space;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Representa un usuari individual i delega les comprovacions de permisos (espai, acció i horari)
// al seu UserGroup
public class User {
  private static final Logger logger = LoggerFactory.getLogger(User.class);

  private final String name;
  private final String credential;
  private UserGroup userGroup;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public String getName() {
    return name;
  }

  // Comprova si l'usuari pot estar en l'espai comprovant les àrees del seu grup.
  public boolean canBeInSpace(Space sp) {
    if (userGroup == null) {
      logger.warn("User {} has no group assigned to check space {}", name, sp.getId());
      return false;
    }
    return userGroup.canBeInSpace(sp);
  }

  // Comprova si l'usuari pot fer una acció comprovant les accions del seu grup.
  public boolean canDoAction(String action) {
    if (userGroup == null) {
      logger.warn("User {} has no group assigned to check action {}", name, action);
      return false;
    }
    return userGroup.canDoAction(action);
  }

  public boolean canDoActionNow(LocalDateTime now) {
    if (userGroup == null) {
      logger.warn("User {} has no group assigned to check schedule at {}", name, now);
      return false;
    }
    return userGroup.canDoActionNow(now);
  }

  public String getCredential() {
    return credential;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }

  public void setUserGroup(UserGroup userGroup) {
    this.userGroup = userGroup;
  }
}
