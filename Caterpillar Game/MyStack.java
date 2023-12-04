package assignment2;

import java.util.NoSuchElementException;

public class MyStack<E> {
    private MyDoublyLinkedList<E> stack;

    public MyStack() {
        stack = new MyDoublyLinkedList<E>();
    }

    public void push(E element) {
        stack.addLast(element);
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.removeLast();
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.peekLast();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void clear() {
        stack.clear();
    }

    public int getSize() {
        return stack.getSize();
    }
}

