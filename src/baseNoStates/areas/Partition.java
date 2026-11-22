package baseNoStates.areas;

import baseNoStates.visitor.Visitor;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Partition extends Area { // Conjunto de Spaces: Basement, ground floor...
  private static final Logger logger = LoggerFactory.getLogger(Partition.class);
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

  // Patro Visitor: se visita a si misma y luego propaga el visitante a cada hijo
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
