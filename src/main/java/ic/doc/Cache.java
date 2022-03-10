package ic.doc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cache implements Weather {

  private final Integer maxCacheSize;
  Map<Tuple<Location, Integer>, Integer> weatherQueries =
      new HashMap<Tuple<Location, Integer>, Integer>();
  ArrayList<Tuple> keys = new ArrayList<>();
  private final Weather forecaster;

  public Cache(Weather forecaster, Integer maxSize) {
    this.forecaster = forecaster;
    this.maxCacheSize = maxSize;
  }

  @Override
  public int temperatureFor(Tuple query) {

    Integer result = weatherQueries.get(query);

    if (result != null) {
      return result;
    }

    if (weatherQueries.size() == maxCacheSize) {
      removeOldest();
    }

    result = this.forecaster.temperatureFor(query);
    weatherQueries.put(query, result);
    keys.add(query);

    return result;
  }

  public void removeOldest() {
    weatherQueries.remove(keys.get(0));
    keys.remove(0);
  }
}
