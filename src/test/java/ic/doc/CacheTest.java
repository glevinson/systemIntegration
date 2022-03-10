package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.time.DayOfWeek;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CacheTest {

  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  Weather weather = context.mock(Weather.class);
  Location location = new Location("London");

  Tuple<Location, DayOfWeek> query = new Tuple<>(location, DayOfWeek.MONDAY);
  Tuple<Location, DayOfWeek> query2 = new Tuple<>(location, DayOfWeek.TUESDAY);
  Tuple<Location, DayOfWeek> query3 = new Tuple<>(location, DayOfWeek.WEDNESDAY);

  @Test
  public void onlyCallsForecastForIfNotInCache() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(weather).temperatureFor(query);
          }
        });
    Cache cache = new Cache(weather, 4);
    cache.temperatureFor(query);
    cache.temperatureFor(query);
  }

  @Test
  public void cacheReturnsCachedValue() {

    Cache cache = new Cache(new Adapter(), 5);
    int temp1 = cache.temperatureFor(query);
    int temp2 = cache.temperatureFor(query);

    assertEquals(temp1, temp2);
  }

  @Test
  public void removesItemsFromCacheIfFull() {

    context.checking(
        new Expectations() {
          {
            exactly(2).of(weather).temperatureFor(query);
            exactly(1).of(weather).temperatureFor(query2);
            exactly(1).of(weather).temperatureFor(query3);
          }
        });

    // Calls query 1, 2, and 3, to show that then the cache will
    // only have query 2 and 3 stored, meaning that tempfor will not
    // be called for those queries again because they are in the cache
    // When query 1 is called again it is not in the cache and
    // therefore tempfor is called again

    Cache cache = new Cache(weather, 2);
    cache.temperatureFor(query);
    cache.temperatureFor(query2);
    cache.temperatureFor(query3);
    cache.temperatureFor(query2);
    cache.temperatureFor(query3);
    cache.temperatureFor(query);
  }

  @Test
  public void adapterGivesReasonableWeather() {
    Adapter adapter = new Adapter();

    Integer test = adapter.temperatureFor(query);

    assertTrue(test > 0 && test < 30);
  }
}
