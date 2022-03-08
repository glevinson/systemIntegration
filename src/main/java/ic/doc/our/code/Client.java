package ic.doc.our.code;

import com.weather.Forecaster;

import java.time.DayOfWeek;

public class Client {
  public static void main(String[] args){

    Cache cache = new Cache(new Adapter());

    int temp = cache.temperatureFor(new Location("London"), DayOfWeek.MONDAY);

    System.out.println("On London it will be  " + temp + " in London");
  }
}
