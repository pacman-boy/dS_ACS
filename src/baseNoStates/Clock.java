package baseNoStates;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// patró observer. Classes implicades: Clock i UnlockedShortly.
// Executa un Timer en paral·lel que notifica cada segon als estats UnlockedShortly subscrits
// sense bloquejar l'execució del servidor principal.
public class Clock extends Observable {
  private static final Logger logger = LoggerFactory.getLogger(Clock.class);
  // Patro Singleton: unica instancia de reloj compartida por todo el sistema
  private static final Clock UNIQUE_INSTANCE = new Clock(1);
  private LocalDateTime date;
  private final Timer timer;
  private final int period;

  private Clock(int period) {
    this.period = period;
    timer = new Timer();
  }

  // Punto de acceso global al Singleton del reloj
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
        logger.debug("run() executed at {}", date);
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
