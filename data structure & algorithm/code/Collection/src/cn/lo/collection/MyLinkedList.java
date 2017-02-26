package cn.lo.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T>{

	private static class Node<T>{
		private T data;
		private Node<T> nextNode;
		private Node<T> previousNode;
		
		public Node(T data, Node<T> nextNode, Node<T> previousNode) {
			this.data = data;
			this.nextNode = nextNode;
			this.previousNode = previousNode;
		}

		public T getData() {
			return data;
		}

		public Node<T> getNextNode() {
			return nextNode;
		}

		public Node<T> getPreviousNode() {
			return previousNode;
		}

		public void setData(T data) {
			this.data = data;
		}

		public void setNextNode(Node<T> nextNode) {
			this.nextNode = nextNode;
		}

		public void setPreviousNode(Node<T> previousNode) {
			this.previousNode = previousNode;
		}
		
		
		
	}
	
	private Node<T> beginNode;
	private Node<T> endNode;
	private int size = 0;
	private int modCount = 0;
	
	public MyLinkedList(){
		clear();
	}
	
	public void clear(){
		size = 0;
		beginNode = new Node<T>(null, null, null);
		endNode = new Node<T>(null,null,beginNode);
		beginNode.setNextNode(endNode);
		modCount++;
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public boolean add(T data){
		return add(data,size());
	}
	
	public boolean add(T data, int index){
		if(index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		addBeforeNode(getNode(index),data);
		return true;
	}
	
	public T get(int index){
		if(index == size()) throw new ArrayIndexOutOfBoundsException();
		return getNode(index).getData();
	}
	
	public T set(T data,int index){
		if(index == size()) throw new ArrayIndexOutOfBoundsException();
		T oldData = getNode(index).getData();
		getNode(index).setData(data);
		return oldData;
	}
	
	public T remove(int index){
		return remove(getNode(index));
	}
	
	public T remove(Node<T> node){
		
		node.getPreviousNode().setNextNode(node.getNextNode());
		node.getNextNode().setPreviousNode(node.getPreviousNode());
		
		size--;
		modCount++;
		
		return node.getData();
	}
	
	private Node<T> getNode(int index){
		if(index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		
		Node<T> node;
		
		if(index < size()/2){
			node = beginNode.getNextNode();
			for(int i=0;i<index;i++)	node = node.getNextNode();
		}
		else{
			node = endNode.getPreviousNode();
			for(int i = size();i>index;i--)	node=node.getPreviousNode();
		}
		return node;
	}
	
	private void addBeforeNode(Node<T> node,T value){
		Node<T> newNode = new Node<T>(value, node, node.getPreviousNode());
		node.getPreviousNode().setNextNode(newNode);
		node.setPreviousNode(newNode);
		modCount++;
		size++;
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<T>{

		private Node<T> currentNode = beginNode.getNextNode();
		private int expectedModCount = modCount;
		boolean okToRemove = false;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return currentNode != endNode;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(expectedModCount != modCount) throw new ConcurrentModificationException();
			if(!hasNext()) throw new NoSuchElementException();
			T data = currentNode.getData();
			currentNode = currentNode.getNextNode();
			okToRemove = true;
			return data;
		}

		public void remove(){
			if(expectedModCount != modCount) throw new ConcurrentModificationException();
			if(!okToRemove)	throw new IllegalStateException();
			MyLinkedList.this.remove(currentNode.getPreviousNode());
			okToRemove = false;
			expectedModCount++;
		}
		
	}

}
