package baseNoStates.doorstates;

import baseNoStates.Door;

public class Locked extends DoorState {
  public Locked(Door door) {
    super(door); // Llama al constructor de la clase padre (DoorState) para guardar la referencia de la puerta.
    state = States.LOCKED;
  }

  @Override
  public void open() {
    System.out.println("The door is locked");
    System.out.println("Can't open door " + door.getId() + " because it's already open");
  }

  @Override
  public void close() {
    System.out.println("Can't close the door " + door.getId() + " because it's already closed");
  }

  @Override
  public void lock() {
    System.out.println("Can't lock door " + door.getId() + " because it's already locked");
  }

  @Override
  public void unlock() {
    door.setState(new Unlocked(door));
  }
}
