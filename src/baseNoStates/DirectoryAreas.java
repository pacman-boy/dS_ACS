package baseNoStates;

import baseNoStates.areas.Area;
import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;

import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryAreas {
  private static ArrayList<Door> allDoors;
  private static Area rootArea;

  public static void makeAreas() {
    // Areas
    Space exterior = new Space("exterior");
    Space stairs = new Space("stairs");
    Space parking = new Space("parking");
    Space hall = new Space("hall");
    Space room1 = new Space("room1");
    Space room2 = new Space("room2");
    Space corridor = new Space("corridor");
    Space room3 = new Space("room3");
    Space it = new Space("IT");

    // Puertas y a que Areas pertenecen.
    // basement(Doors)
    Door d1 = new Door("D1", exterior, parking); // exterior, parking
    parking.addDoor(d1);
    Door d2 = new Door("D2", stairs, parking); // stairs, parking
    parking.addDoor(d2);

    // ground floor(Doors)
    Door d3 = new Door("D3", exterior, hall); // exterior, hall
    hall.addDoor(d3);
    Door d4 = new Door("D4", stairs, hall); // stairs, hall
    hall.addDoor(d4);
    Door d5 = new Door("D5", hall, room1); // hall, room1
    room1.addDoor(d5);
    Door d6 = new Door("D6", hall, room2); // hall, room2
    room2.addDoor(d6);

    // first floor(Doors)
    Door d7 = new Door("D7", stairs, corridor); // stairs, corridor
    corridor.addDoor(d7);
    Door d8 = new Door("D8", corridor, room3); // corridor, room3
    room3.addDoor(d8);
    Door d9 = new Door("D9", corridor, it); // corridor, IT
    it.addDoor(d9);

    // Creacion de partitions y asignacion de areas
    Partition basement = new Partition("basement");
    basement.addArea(parking);

    Partition groundFloor = new Partition("ground_floor");
    groundFloor.addArea(hall);
    groundFloor.addArea(room1);
    groundFloor.addArea(room2);

    Partition floor1 = new Partition("floor1");
    floor1.addArea(room3);
    floor1.addArea(corridor);
    floor1.addArea(it);

    Partition building = new Partition("building");
    building.addArea(basement);
    building.addArea(groundFloor);
    building.addArea(floor1);
    building.addArea(stairs);
    building.addArea(exterior);

    rootArea = building;

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
  }

  public static Area findAreaById(String id) {
    return rootArea.findAreabyId(id);
  }

  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  // this is needed by RequestRefresh
  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }

}
