package cn.lo.collection;

import java.util.Arrays;

public class MyPriorityQueue<E extends Comparable<? super E>> {
	
	private static final int DEFAULT_INITIAL_CAPACITY = 11;

	private  Object[] queue;
	
	private int size;
	
	public MyPriorityQueue(){
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public MyPriorityQueue(int capacity){
		size = 0;
		queue = new Object[capacity];
	}
	
	private void grow(){
		Arrays.copyOf(queue, queue.length + 2);
	}
	
	 public boolean add(E e) {
		 if(e == null){
			 throw new NullPointerException();
		 }
		 if(size == queue.length){
			 grow();
		 }
		 size ++;
		 if(size == 0){
			 queue[0] = e;
		 }
		 else{
			 siftup(size-1,e);
		 }
		 return true;
    }
	 
	 private void siftup(int k, E e){
		 while(k>0){
			if(((Comparable)queue[k/2]).compareTo(e) > 0){
				queue[k] = queue[k/2];
				k=k/2;
			}else{
				break;
			}
		 }
		 queue[k] = e;
	 }
	 
	 public E poll(){
		 if(size == 0){
			 return null;
		 }
		 E result = (E)queue[0];
		 size--;
		 E e = (E)queue[size];
		 queue[size] = null;
		 if(size != 0){
			 siftdown(0,e);
		 }
		 return result;
	 }
	 
	private void siftdown(int k, E e){
		 while(k < size / 2){
			 int child = k * 2 + 1;
			 E c = (E)queue[child];
			 if((child + 1) < size && (c.compareTo((E)queue[child+1]) > 0) ){
				 c = (E)queue[child+1];
				 child ++;
			 }
			 if(c.compareTo(e)>0){
				 break;
			 }
			 queue[k] = c;
			 k = child;
			 
		 }
		 queue[k] = e;
	 }
	
	public static void main(String[] args) {
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<Integer>();
		queue.add(10);
		queue.add(8);
		queue.add(20);
		queue.add(15);
		queue.add(14);
		queue.add(18);
		
		Integer n;
		do{
			 n = queue.poll();
			 System.out.println(n);
		}while(n != null);
	}
	 
}
