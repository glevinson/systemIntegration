package ic.doc.our.code;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

import java.time.DayOfWeek;

public class Adapter implements Weather{

  private final Forecaster forecaster = new Forecaster();

  @Override
  public int temperatureFor(Location location, DayOfWeek day) {
    Region region = Region.valueOf(location.name().toUpperCase());
    Day forecastDay = Day.valueOf(day.name().toUpperCase());
    return this.forecaster.forecastFor(region, forecastDay).temperature();
  }

}
