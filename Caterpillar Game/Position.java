package assignment2;

public class Position {
    private int x;
    private int y;
    
    // constructor that takes x and y coordinates as input
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // copy constructor
    public Position(Position p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    // reset method that takes x and y coordinates as input
    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // reset method that takes a Position object as input
    public void reset(Position p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    // static method to calculate distance between two positions
    public static int getDistance(Position p1, Position p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
    
    // getter methods for x and y coordinates
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    // methods to move the position in different directions
    public void moveWest() {
        x--;
    }
    
    public void moveEast() {
        x++;
    }
    
    public void moveNorth() {
        y--;
    }
    
    public void moveSouth() {
        y++;
    }
    
    // equals method to compare two Position objects
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position p = (Position) o;
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }
}


