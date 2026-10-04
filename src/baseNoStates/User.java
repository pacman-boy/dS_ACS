package baseNoStates;

import baseNoStates.areas.Area;
import baseNoStates.areas.Space;

import java.util.ArrayList;

public class User {
  private final String name;
  private final String credential;
  private ArrayList<Area> areas;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
    areas = new ArrayList<>();
  }

  private ArrayList<Space> getSpaces(){
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : areas) {
      spaces.addAll(area.getSpaces());
    }
    return spaces;
  }

  public boolean canBeInSpace(Space sp){
    return getSpaces().contains(sp);
  }
  public String getCredential() {
    return credential;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }

  public void addArea(Area area){
    areas.add(area);
  }

}
