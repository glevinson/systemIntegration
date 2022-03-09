package ic.doc.our.code;
import java.time.DayOfWeek;

public class Client {
  public static void main(String[] args){

    Cache cache = new Cache(new Adapter());

    int temp = cache.temperatureFor(new Location("London"), DayOfWeek.MONDAY);
    int temp2 = cache.temperatureFor(new Location("London"), DayOfWeek.MONDAY);


    System.out.println("On Monday it will be  " + temp + " in London");
    System.out.println("On Monday it will be  " + temp2 + " in London");

  }
}
