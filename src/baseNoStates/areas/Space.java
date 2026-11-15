package baseNoStates.areas;

import baseNoStates.Door;
import baseNoStates.visitor.Visitor;
import java.util.ArrayList;

public class Space extends Area { // Espacios individuales: Parking, room 1...
  private final ArrayList<Door> doors;

  public Space(String id) {
    super(id);
    doors = new ArrayList<>();
  }

  public ArrayList<Door> getDoorsGivingAccess() {
    return doors;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public void acceptVisitor(Visitor visitor) {
    visitor.visitSpace(this);
  }

  public void addDoor(Door door) {
    this.doors.add(door);
  }
}
