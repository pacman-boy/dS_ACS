package baseNoStates.areas;

import baseNoStates.Door;
import baseNoStates.visitor.Visitor;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Space extends Area { // Espacios individuales: Parking, room 1...
  private static final Logger logger = LoggerFactory.getLogger(Space.class);
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

  // Patro Visitor: nodo hoja, solo llama a visitSpace sobre si mismo
  @Override
  public void acceptVisitor(Visitor visitor) {
    visitor.visitSpace(this);
  }

  public void addDoor(Door door) {
    this.doors.add(door);
  }
}
