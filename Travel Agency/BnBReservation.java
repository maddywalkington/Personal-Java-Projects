package assignment1;

public class BnBReservation extends HotelReservation {
    public BnBReservation(String name, Hotel hotel, String type, int num_nights) {
        super(name, hotel, type, num_nights);
    }

    public int getCost() {
        int roomCost = super.getCost();
        return roomCost + (10 * 100 * getNumOfNights());
    }
}

