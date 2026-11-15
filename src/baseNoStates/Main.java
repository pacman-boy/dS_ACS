package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {
  public static void main(String[] args) {
    // No hace falta llamar a los DirectoryArea y userGroups porque los propios request ya lo
    // llaman
    Clock.getInstance().start();
    new WebServer();
  }
}
