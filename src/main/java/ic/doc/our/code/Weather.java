package ic.doc.our.code;

import java.time.DayOfWeek;

public interface Weather {

  public int temperatureFor(Location location, DayOfWeek day);

}
