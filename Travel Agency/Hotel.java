package assignment1;

public class Hotel {
    private String name;
    private Room[] rooms;
    public Hotel(String name,Room[] rooms) {
        this.name=name;
        this.rooms=new Room[rooms.length];
        for(int i=0;i<rooms.length;i++) {
            this.rooms[i]=new Room(rooms[i]);
        }
    }
    public int reserveRoom(String type) {
        Room room=Room.findAvailableRoom(rooms, type);
        if(room!=null) {
            room.changeAvailability();
            return room.getPrice();
        }
        throw new IllegalArgumentException();
    }
    public boolean cancelRoom(String type) {
        if(Room.makeRoomAvailable(rooms,type)) {
            return true;
        }
        return false;
    }

}
