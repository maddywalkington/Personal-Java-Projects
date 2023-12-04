package assignment2;

public class Caterpillar extends MyDoublyLinkedList<Position> {

    public Caterpillar() {
        Position startPos = new Position(7, 7);
        add(startPos);
    }

    public Position getHead() {
        return peekFirst();
    }

    public void eat(Position pos) {
        if (!isAdjacent(pos, getHead())) {
            throw new IllegalArgumentException();
        }
        addFirst(pos);
    }

    public void move(Position pos) {
        if (!isAdjacentorSame(pos, getHead())) {
            throw new IllegalArgumentException("Input position is not adjacent to the current head position.");
        }
        addFirst(pos);
        removeLast();
    }

    public boolean selfCollision(Position pos) {
        for (Position bodyPart : this) {
            if (bodyPart.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAdjacent(Position pos1, Position pos2) {
        int x1 = pos1.getX();
        int y1 = pos1.getY();
        int x2 = pos2.getX();
        int y2 = pos2.getY();

        return ((x1 == x2 && Math.abs(y1 - y2) == 1) || (y1 == y2 && Math.abs(x1 - x2) == 1));
    }

    private boolean isAdjacentorSame(Position pos1, Position pos2) {
        int x1 = pos1.getX();
        int y1 = pos1.getY();
        int x2 = pos2.getX();
        int y2 = pos2.getY();

        return ((x1 == x2 && Math.abs(y1 - y2) == 1) || (y1 == y2 && Math.abs(x1 - x2) == 1) || x1 == x2 && y1 == y2);
    }
}
