package baseNoStates.areas;

import baseNoStates.visitor.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Patró Composite. Classes implicades: Area, Partition i Space.
// Permet que RequestArea i UserGroup tractin de manera uniforme tant espais individuals (Space)
// com agrupacions d'espais (Partition) dins la jerarquia en arbre de l'edifici.

public abstract class Area { // Tiene tanto Partitions como Spaces
  private static final Logger logger = LoggerFactory.getLogger(Area.class);
  protected final String id;

  public Area(String id) {
    this.id = id;
  }

  public abstract String getId();

  // Patro Visitor: delega el recorrido del arbol al objeto visitante sin conocer el algoritmo
  public abstract void acceptVisitor(Visitor visitor);
}
