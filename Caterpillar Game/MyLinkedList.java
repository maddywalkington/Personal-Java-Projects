package assignment2;

import java.util.Iterator;

public abstract class MyLinkedList<E> implements MyList<E> {

    protected int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}

