package ic.doc;

import com.weather.Day;
import com.weather.Forecaster;
import com.weather.Region;

import java.time.DayOfWeek;

public class Adapter implements Weather{

  private final Forecaster forecaster = new Forecaster();

  @Override
  public int temperatureFor(Tuple query) {

    Location location = (Location) query.getElement_a();
    DayOfWeek day = (DayOfWeek) query.getElement_b();

    Region region = Region.valueOf(location.name().toUpperCase());
    Day forecastDay = Day.valueOf(day.name().toUpperCase());
    return this.forecaster.forecastFor(region, forecastDay).temperature();
  }

}
