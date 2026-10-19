package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
// Encapsula les restriccions temporals (interval de dates, dies de la setmana i franja horària)
// en què un UserGroup té permís per enviar peticions.
public class Schedule {
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
    if (date.isBefore(startDate) || date.isAfter(endDate)) { // compruebo que la fecha está dentro del rango permitido
      return false;
    }

    DayOfWeek day = now.getDayOfWeek();
    if (!daysOfWeek.contains(day)) { // compruebo que el día de la semana está dentro del rango permitido
      return false;
    }

    LocalTime time = now.toLocalTime();
    if (time.isBefore(startTime) || time.isAfter(endTime)) { // compruebo que la hora está dentro del rango permitido
      return false;
    }

    return true;
  }
}
