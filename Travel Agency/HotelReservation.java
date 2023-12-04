package assignment1;

public class HotelReservation extends Reservation {
    private Hotel hotel;
    private String roomType;
    private int numOfNights;
    private int roomPrice;

    public HotelReservation(String name, Hotel hotel, String roomType, int numOfNights) {
        super(name);
        this.hotel = hotel;
        this.roomType = roomType;
        this.numOfNights = numOfNights;
        this.roomPrice = hotel.reserveRoom(roomType);

    }

    public int getNumOfNights() {
        return numOfNights;
    }

    public int getCost() {
        return numOfNights * roomPrice;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof HotelReservation)) {
            return false;
        }

        HotelReservation r = (HotelReservation) o;
        return super.reservationName().equals(r.reservationName())
            && hotel.equals(r.hotel)
            && roomType.equals(r.roomType)
            && numOfNights == r.numOfNights
            && roomPrice == r.roomPrice;
    }
}

