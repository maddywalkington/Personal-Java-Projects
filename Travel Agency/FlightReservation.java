package assignment1;

public class FlightReservation extends Reservation {
  private Airport departure;
  private Airport arrival;

  public FlightReservation(String name, Airport departure, Airport arrival) {
    super(name);
    if (departure.equals(arrival)) {
      throw new IllegalArgumentException("Departure and arrival airports must be different");
    }
    this.departure = departure;
    this.arrival = arrival;
  }

    public int getCost(){
        int dist = Airport.getDistance(arrival, departure);
        double fuel_charge = (dist/167.52)*1.24*100;
        double total = arrival.getFees() + departure.getFees() + fuel_charge + 53.75*100;
        int total_rounded = (int)total;
        if(total != total_rounded)
            total_rounded++;
        return total_rounded;
  }

  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj.getClass() != this.getClass()) return false;
    FlightReservation other = (FlightReservation) obj;
    return super.reservationName().equals(other.reservationName())
        && departure.equals(other.departure)
        && arrival.equals(other.arrival);
  }
}

