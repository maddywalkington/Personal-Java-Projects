package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E> {
	private DNode head;
	private DNode tail;

	public void add(E element) {
		DNode newNode = new DNode();
		newNode.element = element;
		if (tail == null) {
			head = tail = newNode;	
		} else {
			newNode.next = null;
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}




	public E remove() {
		if (tail == null) {
			throw new NoSuchElementException();
		}
		E element = tail.element;
		if (tail.prev == null) {
			head = tail = null;
		} else {
			tail.prev.next = null;
			tail = tail.prev;
		}
		size--;
		return element;
	}

	public void clear() {
		head = tail = null;
		size = 0;
	}

	public void addFirst(E element) {
		DNode newNode = new DNode();
		newNode.element = element;
		if (head == null) {
			head = tail = newNode;
		} else {
			head.prev = newNode;
			head = newNode;
		
		}
		size++;
	}

	public void addLast(E element) {
		add(element);
	}

	public E removeFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		E element = head.element;
		if (head.next == null) {
			head = tail = null;
		} else {
			head.next.prev = null;
			head = head.next;
		}
		size--;
		return element;
	}

	public E removeLast() {
		return remove();
	}

	public E peekFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		return head.element;
	}

	public E peekLast() {
		if (tail == null) {
			throw new NoSuchElementException();
		}
		return tail.element;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof MyDoublyLinkedList)) {
			return false;
		}
		MyDoublyLinkedList<E> other = (MyDoublyLinkedList<E>) obj;
		if (other.getSize() != getSize()) {
			return false;
		}
		Iterator<E> it1 = iterator();
		Iterator<E> it2 = other.iterator();
		while (it1.hasNext()) {
			E element1 = it1.next();
			E element2 = it2.next();
			if (!element1.equals(element2)) {
				return false;
			}
		}
		return true;
	}

	public Iterator<E> iterator() {
		return new DLLIterator();
	}

	private class DNode {
		private E element;
		private DNode next;
		private DNode prev;
	}

	private class DLLIterator implements Iterator<E> {
		DNode curr;

		public DLLIterator() {
			this.curr = head;
		}

		public boolean hasNext() {
			return this.curr != null;
		}

		public E next() {
			if (!this.hasNext())
				throw new NoSuchElementException();
			E element = this.curr.element;
			this.curr = this.curr.next;
			return element;
		}
	}
}
