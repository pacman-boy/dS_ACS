package baseNoStates.areas;
import baseNoStates.Door;
import java.util.List;

public abstract class Area {
  protected String id;


  public abstract List<Door> getDoorsGivingAccess();


}
