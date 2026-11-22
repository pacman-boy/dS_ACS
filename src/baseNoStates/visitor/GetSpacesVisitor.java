package baseNoStates.visitor;

import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetSpacesVisitor implements Visitor {
  private static final Logger logger = LoggerFactory.getLogger(GetSpacesVisitor.class);
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
