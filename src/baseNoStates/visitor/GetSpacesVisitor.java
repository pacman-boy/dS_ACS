package baseNoStates.visitor;

import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;
import java.util.ArrayList;

public class GetSpacesVisitor implements Visitor {
  private final ArrayList<Space> spaces = new ArrayList<>();

  @Override
  public void visitSpace(Space space) {
    spaces.add(space);
  }

  @Override
  public void visitPartition(Partition partition) {
    // El recorrido a los hijos lo hace Partition.acceptVisitor()
  }

  public ArrayList<Space> getSpaces() {
    return spaces;
  }
}
