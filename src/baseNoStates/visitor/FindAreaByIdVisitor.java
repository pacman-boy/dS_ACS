package baseNoStates.visitor;

import baseNoStates.areas.Area;
import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;

public class FindAreaByIdVisitor implements Visitor {
  private final String id;
  private Area foundArea;

  public FindAreaByIdVisitor(String id) {
    this.id = id;
  }

  public Area getFoundArea() {
    return foundArea;
  }

  @Override
  public void visitSpace(Space space) {
    if (foundArea == null && space.getId().equals(id)) {
      foundArea = space;
    }
  }

  @Override
  public void visitPartition(Partition partition) {
    if (foundArea == null && partition.getId().equals(id)) {
      foundArea = partition;
    }
  }
}
