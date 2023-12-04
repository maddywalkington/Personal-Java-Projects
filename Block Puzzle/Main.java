package assignment3;

import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Main {
    public static void main(String args[]) {

        Block b = new Block(0, 0, 0, 0, 0, null, new Block[0]);
        b.updateSizeAndPosition(0, 0, 0);

    }
}