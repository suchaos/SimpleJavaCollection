package com.suchaos;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
	
	private static class Node<T> {
		public T data;
		public Node<T> prev;
		public Node<T> next;
		
		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private int theSize = 0;
	private int modCount = 0;
	private Node<T> beginMarker;
	private Node<T> endMarker;
	
	public MyLinkedList() {
		beginMarker = new Node<T>(null, null, null);
		endMarker = new Node<T>(null, beginMarker, null);
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
	}
	
	public int size() {
		return theSize;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private void addBefore(Node<T> p, T x) {
		Node<T> newNode = new Node<T>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}
	
	private Node<T> getNode(int index, int lower, int upper) {
		Node<T> p;
        if(index < lower || index > upper){
            throw new IndexOutOfBoundsException();
        }
        
        p = beginMarker.next;
    	for(int i = 0; i < index; i++) {
    		p = p.next;
    	}
    	
    	return p;
	}
	
	private Node<T> getNode(int index) {
		return getNode(index, 0, size() - 1);
	}
	
	public T get(int index) {
		return getNode(index).data;
	}
	
	public T set(int index, T newVal) {
		Node<T> p = getNode(index);
		T oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}
	
	public void add(int index, T x) {
		addBefore(getNode(index, 0, size()), x);
	}
	
	public boolean add(T x) {
		add(size(), x);
		return true;
	}
	
	private T remove(Node<T> p) {
		p.prev.next = p.next;
		p.next.prev = p.prev;
		theSize--;
		modCount++;
		return p.data;
	}
	
	public T remove(int index) {
		return remove(getNode(index));
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<T> {
		
		private Node<T> current = beginMarker.next;
		
		@Override
		public boolean hasNext() {
			return current != endMarker;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T nextItem = current.data;
			current = current.next;
			return nextItem;
		}
		
	}
}
