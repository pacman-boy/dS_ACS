package baseNoStates.visitor;

import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;

public interface Visitor {
  void visitSpace(Space space);

  void visitPartition(Partition partition);
}
