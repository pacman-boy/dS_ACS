package baseNoStates.areas;

import baseNoStates.visitor.Visitor;

// Patró Composite. Classes implicades: Area, Partition i Space.
// Permet que RequestArea i UserGroup tractin de manera uniforme tant espais individuals (Space)
// com agrupacions d'espais (Partition) dins la jerarquia en arbre de l'edifici.

public abstract class Area { // Tiene tanto Partitions como Spaces
  protected final String id;

  public Area(String id) {
    this.id = id;
  }

  public abstract String getId();

  public abstract void acceptVisitor(Visitor visitor);
}
