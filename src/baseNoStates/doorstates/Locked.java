package baseNoStates.doorstates;

import baseNoStates.Door;

public class Locked extends DoorState {
  public Locked(Door door) {
    super(door);
    state = States.LOCKED;
  }

  @Override
  public void open() {
    System.out.println("The door is locked");
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

  @Override
  public void unlockShortly() {
    door.setState(new UnlockedShortly(door));
  }
}
