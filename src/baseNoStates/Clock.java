package baseNoStates;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

// patró observer. Classes implicades: Clock i UnlockedShortly.
// Executa un Timer en paral·lel que notifica cada segon als estats UnlockedShortly subscrits
// sense bloquejar l'execució del servidor principal.
public class Clock extends Observable {
  private static final Clock UNIQUE_INSTANCE = new Clock(1);
  private LocalDateTime date;
  private final Timer timer;
  private final int period;

  private Clock(int period) {
    this.period = period;
    timer = new Timer();
  }

  public static Clock getInstance() {
    return UNIQUE_INSTANCE;
  }

  public void start() {
    TimerTask repeatedTask = new TimerTask() {
      @Override
      public void run() {
        date = LocalDateTime.now();
        setChanged();
        notifyObservers();
        System.out.println("run() executed at " + date);
      }
    };
    timer.scheduleAtFixedRate(repeatedTask, 0, 1000L * period);
  }

  public void stop() {
    timer.cancel();
  }

  public int getPeriod() {
    return period;
  }

  public LocalDateTime getDate() {
    return date;
  }
}
