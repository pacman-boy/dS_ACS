package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    // No hace falta llamar a los DirectoryArea y userGroups porque los propios request ya lo
    // llaman
    Clock.getInstance().start();
    new WebServer();
  }
}
