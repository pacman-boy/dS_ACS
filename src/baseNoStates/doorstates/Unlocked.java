package baseNoStates.doorstates;

import baseNoStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Permet obrir i tancar la porta, i bloquejar-la només si està físicament tancada.

public class Unlocked extends DoorState {
  private static final Logger logger = LoggerFactory.getLogger(Unlocked.class);

  public Unlocked(Door door) {
    super(door);
    state = States.UNLOCKED;
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
    if (door.isClosed()) {
      logger.debug("Locking door {}", door.getId());
      door.setState(new Locked(door));
    } else {
      logger.debug("Can't lock door {} because it's open", door.getId());
    }
  }

  @Override
  public void unlock() {
    logger.warn("Can't unlock door {} because it's already unlocked", door.getId());
  }

  @Override
  public void unlockShortly() {
    logger.debug("Can't unlock shortly door {} because it's already unlocked", door.getId());
  }
}
