package cn.lo.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T> {

	private T theItem[];
	private int theSize = 0;
	private static final int DEFAULT_CAPACITY = 10;
	
	public  MyArrayList() {
		// TODO Auto-generated constructor stub
		clear();
	}
	
	public void clear(){
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	public int size(){
		return theSize;
	}
	
	public boolean isEmpty(){
		return theSize == 0;
	}
	
	public void trimToSize(){
		ensureCapacity(theSize);
	}
	
	public T get(int index){
		if(index > (theSize-1) || index < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		else
			return theItem[index];
	}
	
	public void set(int index,T value){
		if(index > (theSize-1) || index < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		else{
			theItem[index] = value;
		}
	}
	
	public  void  ensureCapacity(int capacity){
		if(theSize > capacity) return;
		T oldItem[] = theItem;
		theItem = (T[])new Object[capacity];
		for(int i = 0; i < oldItem.length; i++){
			theItem[i] = oldItem[i];
		}
	}
	
	public boolean add(T value){
		return add(value,theSize);
	}
	
	public boolean add(T value , int index){
		if(index > (theSize) || index < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		else{
			if(theSize == theItem.length){
				ensureCapacity(2*theSize);
			}
			for(int i = theSize ; i >index ; i--){
				theItem[i] = theItem[i-1];
			}
			theItem[index] = value; 
			theSize++;
				
		}
		return true;
	}
	
	public boolean remove(int index){
		if(index > (theSize-1) || index < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		else{
			for(int i = index ; i < theSize-1 ; i++)	theItem[i] = theItem[i+1];
		}
		theSize--;
		return true;
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T>{

		private int current = 0;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (current < size());
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(hasNext())	return theItem[current++];
			else
				throw new NoSuchElementException();
		}
		
		public void remove(){
			MyArrayList.this.remove(--current);
		}
		
	}

}
