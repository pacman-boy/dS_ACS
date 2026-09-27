package baseNoStates.doorstates;

import baseNoStates.Door;

public class Unlocked extends DoorState {
  public Unlocked(Door door){
    super(door); // Llama al constructor de la clase padre (DoorState) para guardar la referencia de la puerta.
    state = States.UNLOCKED;
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
    } else {
      System.out.println("Can't open door " + door.getId() + " because it's already open");
    }
  }

  @Override
  public void close() {
    if (door.isClosed()) {
      System.out.println("Can't close door " + door.getId() + " because it's already closed");
    } else {
      door.setClosed(true);
    }
  }

  @Override
  public void lock() {
    if (door.isClosed()) {
      door.setState(new Locked(door));
    } else {
      System.out.println("Can't lock an open door");
    }
  }

  @Override
  public void unlock() {
    System.out.println("Can't unlock door " + door.getId() + " because it's already unlocked");
  }
}
