package assignment2;

public class ActionQueue extends MyQueue<Direction> {

    public ActionQueue() {
        super();
    }

    public void clear() {
        super.clear();
    }

    public void loadFromEncodedString(String encodedString) throws IllegalArgumentException {
        String n = "";
        for (int i = 0; i < encodedString.length(); i++) {
            char c = encodedString.charAt(i);
            if (c == '[') {
                int k = Integer.parseInt(n);
                // parse the direction(s) D
                String d = encodedString.substring(i + 1, encodedString.indexOf(']', i));
                for (int j = 0; j < k; j++) {
                    // add the Direction object to the queue
                    for (int l = 0; l < d.length(); l++) {
                        char dir = d.charAt(l);
                        switch (dir) {
                            case 'N':
                                super.enqueue(Direction.NORTH);
                                break;
                            case 'S':
                                super.enqueue(Direction.SOUTH);
                                break;
                            case 'W':
                                super.enqueue(Direction.WEST);
                                break;
                            case 'E':
                                super.enqueue(Direction.EAST);
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid direction: " + dir);
                        }
                    }
                }
                n = "";
            } else if (Character.isDigit(c)) {
                n += c;
                char a = encodedString.charAt(i+1);
                if (a != '[' && !Character.isDigit(a)){
                    throw new IllegalArgumentException("AAAAA");}

            }
        }
    }
}

    
