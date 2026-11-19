package baseNoStates.doorstates;

import baseNoStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Estat concret del patró State. Impedeix obrir la porta fins que passi a Unlocked o
// UnlockedShortly.
public class Locked extends DoorState {
  private static final Logger logger = LoggerFactory.getLogger(Locked.class);

  public Locked(Door door) {
    super(door);
    state = States.LOCKED;
  }

  @Override
  public void open() {
    logger.debug("Can't open door {} because it's locked", door.getId());
  }

  @Override
  public void close() {
    logger.debug("Can't close door {} because it's already closed", door.getId());
  }

  @Override
  public void lock() {
    logger.debug("Can't lock door {} because it's already locked", door.getId());
  }

  @Override
  public void unlock() {
    logger.debug("Unlocking door {}", door.getId());
    door.setState(new Unlocked(door));
  }

  @Override
  public void unlockShortly() {
    logger.debug("Unlocking shortly door {}", door.getId());
    door.setState(new UnlockedShortly(door));
  }
}
