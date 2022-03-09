package ic.doc.our.code;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class Cache implements Weather {

  private final Weather forecaster;
  private final Integer maxCacheSize = 10;

  private Map<Tuple<Location, DayOfWeek>, Integer> weatherQueries = new HashMap<>();

  public Cache(Weather forecaster) {
    this.forecaster = forecaster;
  }

  @Override
  public int temperatureFor(Location location, DayOfWeek day) {
    Tuple<Location, DayOfWeek> query = new Tuple<>(location, day);
    Integer result = weatherQueries.get(query);
    if (result != null) {
      System.out.println("Did use cache");
      return result;
    }
    System.out.println("Did not use cache");
    if ( weatherQueries.size() == maxCacheSize ){
      // add remove function here
    }
    result = this.forecaster.temperatureFor(location, day);
    weatherQueries.put(query, result);
    return result;
  }

}
