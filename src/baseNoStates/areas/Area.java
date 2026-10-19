package baseNoStates.areas;
import baseNoStates.Door;

import java.util.ArrayList;

// Patró Composite. Classes implicades: Area, Partition i Space.
// Permet que RequestArea i UserGroup tractin de manera uniforme tant espais individuals (Space)
// com agrupacions d'espais (Partition) dins la jerarquia en arbre de l'edifici.

public abstract class Area { // Tiene tanto Partitions como Spaces
  protected String id;

  public Area(String id){ this.id = id; }

  public abstract ArrayList<Door> getDoorsGivingAccess();
  public abstract Area findAreabyId(String id);
  public abstract ArrayList<Space> getSpaces();
  public abstract String getId();


}
