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


  @Test
  public void cacheReturnsCachedValue(){

    Cache cache = new Cache(new Adapter());



    int temp1 = cache.temperatureFor(location, DayOfWeek.MONDAY);
    int temp2 = cache.temperatureFor(location, DayOfWeek.MONDAY);

    assertEquals(temp1, temp2);

  }


  @Test
  public void storesTemperatureInCache(){

  }


}
