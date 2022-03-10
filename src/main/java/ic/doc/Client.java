package ic.doc;

import java.time.DayOfWeek;

public class Client {
  public static void main(String[] args) {

    Cache cache = new Cache(new Adapter(), 5);
    Location london = new Location("London");

    Tuple<Location, DayOfWeek> query = new Tuple<>(london, DayOfWeek.MONDAY);

    int temp = cache.temperatureFor(query);
    System.out.println("On Monday it will be  " + temp + " in London");

    int temp2 = cache.temperatureFor(query);
    System.out.println("On Monday it will be  " + temp2 + " in London");
  }
}
