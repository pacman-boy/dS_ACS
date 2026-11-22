package baseNoStates.visitor;

import baseNoStates.Door;
import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;
import java.util.ArrayList;

public class GetDoorsGivingAccessVisitor implements Visitor {
  private final ArrayList<Door> doors = new ArrayList<>();

  public ArrayList<Door> getDoors() {
    return doors;
  }

  @Override
  public void visitSpace(Space space) {
    for (Door door : space.getDoorsGivingAccess()) {
      if (!doors.contains(door)) {
        doors.add(door);
      }
    }
  }

  @Override
  public void visitPartition(Partition partition) {
    // No hace falta hacer nada aquí, ya que al hacer Partition.acceptVisitor() recorre sus hijos
  }
}
