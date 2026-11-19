package baseNoStates.doorstates;

import baseNoStates.Clock;
import baseNoStates.Door;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Forma part del patro state i patro Observer (Clock)
// Quan passa aaquest estat es subscriu al Clock 10 segons i es desubscriu

public class UnlockedShortly extends DoorState implements Observer {
  private static final Logger logger = LoggerFactory.getLogger(UnlockedShortly.class);
  private int remainingSeconds = 10;

  public UnlockedShortly(Door door) {
    super(door);
    state = States.UNLOCKED_SHORTLY;
    Clock.getInstance().addObserver(this);
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      logger.debug("Door {} opened", door.getId());
    } else {
      logger.debug("Can't open door {} because it's already open", door.getId());
    }
  }

  @Override
  public void close() {
    if (door.isClosed()) {
      logger.debug("Can't close door {} because it's already closed", door.getId());
    } else {
      door.setClosed(true);
      logger.debug("Door {} closed", door.getId());
    }
  }

  @Override
  public void lock() {
    logger.debug("Can't lock door {} because it's unlocked shortly", door.getId());
  }

  @Override
  public void unlock() {
    logger.debug("Can't unlock door {} because it's already unlocked shortly", door.getId());
  }

  @Override
  public void unlockShortly() {
    logger.debug("Can't unlock shortly door {} because it's already unlocked shortly", door.getId());
  }

  @Override
  public void update(Observable o, Object arg) {
    remainingSeconds--;
    if (remainingSeconds <= 0) {
      Clock.getInstance().deleteObserver(this);
      if (door.isClosed()) {
        logger.debug("Door {} was closed after 10s, returning to Locked", door.getId());
        door.setState(new Locked(door));
      } else {
        logger.warn("Door {} enters the propped state (remained open after 10 seconds)", door.getId());
        door.setState(new Propped(door));
      }
    }
  }
}
