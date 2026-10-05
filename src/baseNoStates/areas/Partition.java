package baseNoStates.areas;

import baseNoStates.Door;
import java.util.ArrayList;

public class Partition extends Area{ // Conjunto de Spaces: Basement, ground floor...
  private ArrayList<Area> area; // Array tipo Area, ya que puede ser tanto Partitions como Spaces

  public Partition(String id){
    super(id);
    this.area = new ArrayList<>();
  }
  @Override
  public ArrayList<Door> getDoorsGivingAccess() // Doors que DAN ACCESO a this. (toSpace)
  {
    ArrayList<Door> doorsGivingAccess = new ArrayList<>();
    for (Area area : this.area) {
      doorsGivingAccess.addAll(area.getDoorsGivingAccess());
    }
    return doorsGivingAccess;
  }

  @Override
  public Area findAreabyId(String id) { // recorrer las hojas de this.area en
                                        // busca de un partition/space con un id concreto.
    if (this.id.equals(id)){
      return this;
    }

    for (Area area : this.area){
      Area tempArea = area.findAreabyId(id);
      if (tempArea != null){
        return tempArea;
      }
    }
    return null;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : this.area){
      spaces.addAll(area.getSpaces());
    }
    return spaces;
  }

  @Override
  public String getId() {
    return this.id;
  }

  public void addArea(Area area){ // Se llama desde DirectoryAreas
    this.area.add(area);
  } // en vez de modificar el constructor, se usa en DirectoryAreas.1
}
