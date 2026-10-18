package baseNoStates.doorstates;

import baseNoStates.Door;

public class Propped extends DoorState {
  public Propped(Door door) {
    super(door);
    state = States.PROPPED;
  }

  @Override
  public void open() {
    System.out.println("Can't open the door " + door.getId() + " because it's already open");
  }

  @Override
  public void close() {
    door.setClosed(true);
    door.setState(new Locked(door));
  }

  @Override
  public void lock() {
    System.out.println("Can't lock the door " + door.getId() + " because it's propped");
  }

  @Override
  public void unlock() {
    System.out.println("Can't unlock door " + door.getId() + " because it's propped");
  }

  @Override
  public void unlockShortly() {
    System.out.println("Can't unlock shortly door " + door.getId() + " because it's propped");
  }

}
