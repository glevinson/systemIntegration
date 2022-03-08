package ic.doc.our.code;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache implements Weather {

  private final Weather forecaster;
  private final Integer maxCacheSize = 10;

  private Map<Tuple<Location, DayOfWeek>, Integer> weatherQueries = new LinkedHashMap<>();

  public Cache(Weather forecaster) {
    this.forecaster = forecaster;
  }

  @Override
  public int temperatureFor(Location location, DayOfWeek day) {
    Tuple<Location, DayOfWeek> query = new Tuple<>(location, day);
    Integer result = weatherQueries.get(query);
    if (result != null) {
      return result;
    }
    if ( weatherQueries.size() == maxCacheSize ){
      Tuple<Location, DayOfWeek> oldestKey = oldestKey();
      weatherQueries.remove( oldestKey );
    }
    result = this.forecaster.temperatureFor(location, day);
    weatherQueries.put(query, result);
    return result;
  }

  private Tuple<Location, DayOfWeek> oldestKey() {
    return weatherQueries.keySet().iterator().next();


  }
}
