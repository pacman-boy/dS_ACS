package baseNoStates;

import baseNoStates.areas.Area;
import baseNoStates.areas.Space;

import java.util.ArrayList;

public class User {
  private final String name;
  private final String credential;
  private UserGroup userGroup;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public boolean canBeInSpace(Space sp) { // Comprova si l'usuari pot estar en l'espai comprovant les areas del seu grup.
    if (userGroup == null) {
      return false;
    }
    return userGroup.canBeInSpace(sp);
  }

  public boolean canDoAction(String action) { // Comprova si l'usuari pot fer una accio comprovant les accions del seu grup.
    if (userGroup == null) {
      return false;
    }
    return userGroup.canDoAction(action);
  }

  public boolean canDoActionNow(LocalDateTime now) {
    if (userGroup == null) {
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

  public void setUserGroup(UserGroup userGroup){
    this.userGroup = userGroup;
  }

}
