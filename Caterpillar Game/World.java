package assignment2;

public class World {
    private Caterpillar caterpillar;
    private Position foodPosition;
    private Region region;
    private ActionQueue actionQueue;
    private TargetQueue targetQueue;
    private GameState gameState;

    public World(TargetQueue targetQueue, ActionQueue actionQueue) {
        this.targetQueue = targetQueue;
        this.actionQueue = actionQueue;
        this.region = new Region(0, 0, 15, 15);
        this.caterpillar = new Caterpillar();
        this.foodPosition = targetQueue.dequeue();
        this.gameState = GameState.MOVE;
    }

    public void step() {
        if (actionQueue.isEmpty()) {
            gameState = GameState.NO_MORE_ACTION;
            return;
        }

        Direction direction = actionQueue.dequeue();

        if (gameState != GameState.MOVE && gameState != GameState.EAT) {
            return;
        }



        Position head = caterpillar.getHead();
        Position nextPosition = new Position(head);

        if (direction.equals(Direction.NORTH)) {
            nextPosition.moveNorth();
        } else if (direction.equals(Direction.SOUTH)) {
            nextPosition.moveSouth();
        } else if (direction.equals(Direction.WEST)) {
            nextPosition.moveWest();
        } else if (direction.equals(Direction.EAST)) {
            nextPosition.moveEast();
        }

        if (!region.contains(nextPosition)) {
            gameState = GameState.WALL_COLLISION;
            return;
        }

//        if (caterpillar.selfCollision(nextPosition)) {
//            gameState = GameState.SELF_COLLISION;
//            return;
//        }

        if (nextPosition.equals(foodPosition)) {
            caterpillar.eat(foodPosition);
            caterpillar.move(nextPosition);

            if (targetQueue.isEmpty()) {
                gameState = GameState.DONE;
                return;
            }
            else {
                foodPosition = targetQueue.dequeue();
                gameState = GameState.EAT;
                return;
            }

        }

        gameState = GameState.MOVE;

        if (direction.equals(Direction.NORTH)) {
            head.moveNorth();
        } else if (direction.equals(Direction.SOUTH)) {
            head.moveSouth();
        } else if (direction.equals(Direction.WEST)) {
            head.moveWest();
        } else if (direction.equals(Direction.EAST)) {
            head.moveEast();
        }

    }

    public GameState getState() {
        return gameState;
    }

    public Caterpillar getCaterpillar() {
        return caterpillar;
    }

    public Position getFood() {
        return foodPosition;
    }

    public boolean isRunning() {
        return gameState == GameState.MOVE || gameState == GameState.EAT;
    }
}


