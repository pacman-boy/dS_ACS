package baseNoStates.areas;
import baseNoStates.Door;

import java.util.ArrayList;

public abstract class Area { // Tiene tanto Partitions como Spaces
  protected String id;

  public Area(String id){ this.id = id; }

  public abstract ArrayList<Door> getDoorsGivingAccess();
  public abstract Area findAreabyId(String id);
  public abstract ArrayList<Space> getSpaces();
  public abstract String getId();


}
