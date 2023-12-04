package assignment1;

public class Basket {
    private Reservation[] reservations;
    private int numOfReservations;

    public Basket() {
        this.reservations = new Reservation[10];
        this.numOfReservations = 0;
    }

    public Reservation[] getProducts() {
        Reservation[] copy = new Reservation[numOfReservations];
        System.arraycopy(reservations, 0, copy, 0, numOfReservations);
        return copy;
    }

    public int add(Reservation reservation) {
        reservations[numOfReservations++] = reservation;
        return numOfReservations;
    }

    public boolean remove(Reservation reservation) {
        for (int i = 0; i < numOfReservations; i++) {
            if (reservations[i].equals(reservation)) {
                for (int j = i; j < numOfReservations - 1; j++) {
                    reservations[j] = reservations[j + 1];
                }
                numOfReservations--;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        reservations = new Reservation[10];
        numOfReservations = 0;
    }

    public int getNumOfReservations() {
        return numOfReservations;
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (int i = 0; i < numOfReservations; i++) {
            totalCost += reservations[i].getCost();
        }
        return totalCost;
    }
}

