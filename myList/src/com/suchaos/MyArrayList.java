package com.suchaos;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T>{
	private static final int DEFAULT_CAPACITY = 10;
	private int theSize;
	private T[] theItems;
	
	public MyArrayList() {
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	public int size() {
		return theSize;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public T get(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return theItems[index];
	}
	
	public T set(int index, T newVal) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		T oldVal = theItems[index];
		theItems[index] = newVal;
		return oldVal;
	}
	
	public void add(int index, T x) {
		if(theItems.length == size()) {
			ensureCapacity(size() * 2 + 1);
		}
		for(int i = theSize; i > index; i--) {
			theItems[i] = theItems[i-1];
		}
		theItems[index] = x;
		theSize++;
	}
	
	public void add(T x) {
		add(size(), x);
	}
	
	public T remove(int index) {
		T removedItem = theItems[index];
		for(int i = index; i < size(); i++) {
			theItems[i] = theItems[i+1];
		}
		theSize--;
		return removedItem;
	}

	private void ensureCapacity(int newCapacity) {
		if(newCapacity < theSize) {
			return;
		}
		T[] old = theItems;
		theItems = (T[])new Object[newCapacity];
		for(int i = 0; i < size(); i++) {
			theItems[i] = old[i];
		}
		
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T> {
		
		private int current = 0;

		@Override
		public boolean hasNext() {
			return current < size();
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return theItems[current++];
		}
		
	}
	
	

}
