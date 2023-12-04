package assignment2;

import java.util.NoSuchElementException;

public class MyQueue<E> {

    private MyDoublyLinkedList<E> list;

    public MyQueue() {
        list = new MyDoublyLinkedList<>();
    }

    public void enqueue(E element) {
        list.addLast(element);
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }


public boolean equals(Object o) {
  return o.equals(list);}



    private int getSize() {
        return list.getSize();
    }

}

