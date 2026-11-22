package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Encapsula les restriccions temporals (interval de dates, dies de la setmana i franja horària)
// en què un UserGroup té permís per enviar peticions.
public class Schedule {
  private static final Logger logger = LoggerFactory.getLogger(Schedule.class);
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final ArrayList<DayOfWeek> daysOfWeek;
  private final LocalTime startTime;
  private final LocalTime endTime;

  public Schedule(LocalDate startDate, LocalDate endDate, ArrayList<DayOfWeek> daysOfWeek,
      LocalTime startTime, LocalTime endTime) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.daysOfWeek = daysOfWeek;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public boolean canSendRequests(LocalDateTime now) {
    LocalDate date = now.toLocalDate(); // solo la fecha
    // compruebo que la fecha está dentro del rango permitido
    if (date.isBefore(startDate) || date.isAfter(endDate)) {
      return false;
    }

    DayOfWeek day = now.getDayOfWeek();
    // compruebo que el día de la semana está dentro del rango permitido
    if (!daysOfWeek.contains(day)) {
      return false;
    }

    LocalTime time = now.toLocalTime();
    // compruebo que la hora está dentro del rango permitido
    return !time.isBefore(startTime) && !time.isAfter(endTime);
  }
}
