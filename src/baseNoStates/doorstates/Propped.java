package baseNoStates.doorstates;

import baseNoStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Estat concret del patró State. S'hi arriba quan la porta segueix oberta després dels 10s
// d'UnlockedShortly;
// en tancar-la físicament (close), passa automàticament a l'estat Locked.
public class Propped extends DoorState {
  private static final Logger logger = LoggerFactory.getLogger(Propped.class);

  public Propped(Door door) {
    super(door);
    state = States.PROPPED;
  }

  @Override
  public void open() {
    logger.debug("Can't open door {} because it's already open", door.getId());
  }

  @Override
  public void close() {
    logger.debug("Door {} closed from propped state, transitioning to Locked", door.getId());
    door.setClosed(true);
    door.setState(new Locked(door));
  }

  @Override
  public void lock() {
    logger.debug("Can't lock door {} because it's propped", door.getId());
  }

  @Override
  public void unlock() {
    logger.debug("Can't unlock door {} because it's propped", door.getId());
  }

  @Override
  public void unlockShortly() {
    logger.debug("Can't unlock shortly door {} because it's propped", door.getId());
  }
}
