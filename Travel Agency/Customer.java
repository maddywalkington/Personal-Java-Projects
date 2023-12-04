package assignment1;

public class Customer {
    private String name;
    private int balance;
    private Basket basket;

    public Customer(String name, int initialBalance) {
        this.name = name;
        this.balance = initialBalance;
        this.basket = new Basket();
    }

    public String getName() {
        return this.name;
    }

    public int getBalance() {
        return this.balance;
    }

    public Basket getBasket() {
        return this.basket;
    }

    public int addFunds(int amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        this.balance += amount;
        return this.balance;
    }
    public int addToBasket(Reservation reservation) throws IllegalArgumentException {
        if (!reservation.reservationName().equals(this.name)) {
            throw new IllegalArgumentException();
        }
        this.basket.add(reservation);
        return this.basket.getProducts().length;
    }

    public int addToBasket(Hotel hotel, String roomType, int nights, boolean breakfast) {
        Reservation reservation = new HotelReservation(this.name, hotel, roomType, nights);
        this.basket.add(reservation);
        return this.basket.getProducts().length;
    }

    public int addToBasket(Airport departure, Airport destination) {
        try {
            Reservation reservation = new FlightReservation(this.name, departure, destination);
            this.basket.add(reservation);
        } catch (IllegalArgumentException e) {
            // do nothing
        }
        return this.basket.getProducts().length;
    }

    public boolean removeFromBasket(Reservation reservation) {
        return this.basket.remove(reservation);
    }

    public int checkOut() throws IllegalStateException {
        int totalCost = this.basket.getTotalCost();
        if (totalCost > this.balance) {
            throw new IllegalStateException();
        }
        this.balance -= totalCost;
        this.basket.clear();
        return this.balance;
    }
}
