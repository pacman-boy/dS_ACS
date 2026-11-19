package baseNoStates.doorstates;

import baseNoStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Utilitzem el patró state per evitar if-else per el canvi d'estat de Door

public abstract class DoorState {
  private static final Logger logger = LoggerFactory.getLogger(DoorState.class);

  protected final Door door;
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
