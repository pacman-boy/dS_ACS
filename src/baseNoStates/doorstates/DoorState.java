package baseNoStates.doorstates;
import baseNoStates.Door;

import java.util.Observer;
// Utilitzem el patró state per evitar if-else per el canvi d'estat de Door

public abstract class DoorState {
  protected Door door;
  protected String state;

  public DoorState(Door door) {
    this.door = door;
  }

  public String getStateName() {
    return state;
  }

  public abstract void open();
  public abstract void close();
  public abstract void lock();
  public abstract void unlock();
  public abstract void unlockShortly();
}
