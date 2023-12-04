package assignment2;

public class TargetQueue extends MyQueue<Position> {

    private MyStack<String> stack;

    public TargetQueue() {
        super();
        stack = new MyStack<>();
    }

    public void clear() {
        super.clear();
        stack.clear();
    }

    public void addTargets(String input) throws IllegalArgumentException {
        String[] positions = input.split("\\.");
        for (String position : positions) {
            if (position.matches("\\(\\d+,\\d+\\)")) {
                int x = Integer.parseInt(position.substring(1, position.indexOf(",")));
                int y = Integer.parseInt(position.substring(position.indexOf(",") + 1, position.length() - 1));
                super.enqueue(new Position(x, y));
            } else {
                throw new IllegalArgumentException("Invalid position syntax: " + position);
            }
        }
    }
}

