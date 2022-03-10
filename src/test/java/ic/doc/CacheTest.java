package ic.doc;

import org.jmock.Expectations;
import org.jmock.api.Expectation;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;
import org.junit.Rule;

import java.time.DayOfWeek;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


public class CacheTest {

  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  Weather weather = context.mock(Weather.class);
  Location location = new Location("London");

  Tuple<Location, DayOfWeek> query = new Tuple<>(location, DayOfWeek.MONDAY);
  Tuple<Location, DayOfWeek> query2 = new Tuple<>(location, DayOfWeek.TUESDAY);
  Tuple<Location, DayOfWeek> query3 = new Tuple<>(location, DayOfWeek.WEDNESDAY);


  @Test
  public void onlyCallsForecastForIfNotInCache(){
    context.checking(new Expectations(){{
      exactly(1).of(weather).temperatureFor(query);
    }});
    Cache cache = new Cache(weather);
    cache.temperatureFor(query);
    cache.temperatureFor(query);
  }

  @Test
  public void cacheReturnsCachedValue(){

    Cache cache = new Cache(new Adapter());
    int temp1 = cache.temperatureFor(query);
    int temp2 = cache.temperatureFor(query);

    assertEquals(temp1, temp2);

  }

  @Test
  public void removesItemsFromCacheIfFull(){

    context.checking(new Expectations(){{
      exactly(2).of(weather).temperatureFor(query);
      exactly(1).of(weather).temperatureFor(query2);
      exactly(1).of(weather).temperatureFor(query3);
    }});

    Cache cache = new Cache(weather);
    int temp1 = cache.temperatureFor(query);
    int temp2 = cache.temperatureFor(query2);
    int temp3 = cache.temperatureFor(query3);
    int temp4 = cache.temperatureFor(query2);
    int temp5 = cache.temperatureFor(query3);
    int temp6 = cache.temperatureFor(query);

  }



}
