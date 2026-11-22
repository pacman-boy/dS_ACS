package baseNoStates.visitor;

import baseNoStates.Door;
import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;
import baseNoStates.doorstates.States;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetProppedDoorsVisitor implements Visitor {
  private static final Logger logger = LoggerFactory.getLogger(GetProppedDoorsVisitor.class);
  private final ArrayList<Door> proppedDoors = new ArrayList<>();

  public ArrayList<Door> getProppedDoors() {
    return proppedDoors;
  }

  @Override
  public void visitSpace(Space space) {
    for (Door door : space.getDoorsGivingAccess()) {
      if (States.PROPPED.equals(door.getStateName()) && !proppedDoors.contains(door)) {
        proppedDoors.add(door);
      }
    }
  }

  @Override
  public void visitPartition(Partition partition) {
    // El recorrido a los hijos lo hace Partition.acceptVisitor()
  }
}
