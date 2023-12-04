package assignment1;

public class Room {
    private String type;
    private int price;
    private boolean availability;

    public Room(String type) {
        switch (type) {
            case "double":
                this.type = type;
                this.price = 9000;
                break;
            case "queen":
                this.type = type;
                this.price = 11000;
                break;
            case "king":
                this.type = type;
                this.price = 15000;
                break;
            default:
                throw new IllegalArgumentException();
        }
        this.availability = true;
    }

    public Room(Room room) {
        this.type = room.type;
        this.price = room.price;
        this.availability = room.availability;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public void changeAvailability() {
        availability = !availability;
    }

    public static Room findAvailableRoom(Room[] rooms, String type) {
        for (Room room : rooms) {
            if (room.type.equals(type) && room.availability) {
                return room;
            }
        }
        return null;
    }

    public static boolean makeRoomAvailable(Room[] rooms, String type) {
        for (Room room : rooms) {
            if (room.type.equals(type) && !room.availability) {
                room.availability = true;
                return true;
            }
        }
        return false;
    }
}
