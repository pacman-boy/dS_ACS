package baseNoStates.areas;

import baseNoStates.Door;
import java.util.ArrayList;


public class Space extends Area{ // Espacios individuales: Parking, room 1...
  private ArrayList<Door> doors;

  public Space(String id){
    super(id);
    doors = new ArrayList<>();
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() { return doors; }

  @Override
  public Area findAreabyId(String id) {
    if (this.id.equals(id)){ // IDE recomienda para strings mejor equals
      return this;
    }
    return null;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    spaces.add(this);
    return spaces;
  }

  @Override
  public String getId() {
    return this.id;
  }

  public void addDoor(Door door) {
    this.doors.add(door);
  }

}
