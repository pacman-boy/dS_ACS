package baseNoStates.areas;

import baseNoStates.visitor.Visitor;
import java.util.ArrayList;

public class Partition extends Area { // Conjunto de Spaces: Basement, ground floor...
  // Array tipo Area, ya que puede ser tanto Partitions como Spaces
  private final ArrayList<Area> area;

  public Partition(String id) {
    super(id);
    this.area = new ArrayList<>();
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public void acceptVisitor(Visitor visitor) {
    visitor.visitPartition(this);
    for (Area child : this.area) {
      child.acceptVisitor(visitor);
    }
  }

  public void addArea(Area area) { // Se llama desde DirectoryAreas
    this.area.add(area);
  } // en vez de modificar el constructor, se usa en DirectoryAreas.1
}
