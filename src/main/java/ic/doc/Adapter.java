package ic.doc;

import com.weather.Day;
import com.weather.Forecaster;
import com.weather.Region;

import java.time.DayOfWeek;

public class Adapter implements Weather {

  private final Forecaster forecaster = new Forecaster();

  @Override
  public int temperatureFor(Tuple query) {

    Location location = (Location) query.getElementA();
    DayOfWeek day = (DayOfWeek) query.getElementB();

    Region region = Region.valueOf(location.name().toUpperCase());
    Day forecastDay = Day.valueOf(day.name().toUpperCase());
    return this.forecaster.forecastFor(region, forecastDay).temperature();
  }
}
