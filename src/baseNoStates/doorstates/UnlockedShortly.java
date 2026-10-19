package baseNoStates.doorstates;

import baseNoStates.Clock;
import baseNoStates.Door;

import java.util.Observable;
import java.util.Observer;
// Forma part del patro state i patro Observer (Clock)
// Quan passa aaquest estat es subscriu al Clock 10 segons i es desubscriu

public class UnlockedShortly extends DoorState implements Observer {
  private int remainingSeconds = 10;

  public UnlockedShortly(Door door) {
    super(door);
    state = States.UNLOCKED_SHORTLY;
    Door.getClock().addObserver(this);
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
    System.out.println("Can't lock door " + door.getId() + " because it's unlocked shortly");
  }

  @Override
  public void unlock() {
    System.out.println("Can't unlock door " + door.getId() + " because it's already unlocked shortly");
  }

  @Override
  public void unlockShortly() {
    System.out.println("Can't unlock shortly door " + door.getId() + " because it's already unlocked shortly");
  }

  @Override
  public void update(Observable o, Object arg) {
    remainingSeconds--;
    if (remainingSeconds <= 0) {
      Door.getClock().deleteObserver(this);
      if (door.isClosed()) {
        door.setState(new Locked(door));
      } else {
        door.setState(new Propped(door));
      }
    }
  }
}
