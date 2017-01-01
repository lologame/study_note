package cn.lo.collection;

public class BinarySearchTree<T extends Comparable<? super T>> {
	private static class BinaryNode<T>{
		private BinaryNode<T> left;
		private BinaryNode<T> right;
		private T element;
		public BinaryNode(BinaryNode<T> left, BinaryNode<T> right, T element) {
			this.left = left;
			this.right = right;
			this.element = element;
		}
		public BinaryNode(T element){
			this(null,null,element);
		}
	}
	
	private BinaryNode<T> root;
	
	public BinarySearchTree(){
		root = null;
	}
	
	public void makeEmpty(){
		root = null;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public boolean contains(T x){
		
		return contains(x,root);
	}
	
	private boolean contains(T x , BinaryNode<T> node){
		
		if(node == null)	return false;
		
		int compareResult = x.compareTo(node.element);
		
		if(compareResult < 0)
			return contains(x,node.left);
		else if ( compareResult > 0)
			return contains(x,node.right);
		else
			return true;
	
	}
	
	public T findMin(){
		return findMin(root).element;
	}
	
	private BinaryNode<T> findMin(BinaryNode<T> node){
		if(node.left != null)	return findMin(node.left);
		else return node;
	}
	
	public T findMax(){
		return findMax(root).element;
	}
	
	private BinaryNode<T> findMax(BinaryNode<T> node){
		if(node.right != null)	return findMin(node.right);
		else return node;
	}
	
	public void insert(T x){
		insert(x,root);
	}
	
	private BinaryNode<T> insert(T x , BinaryNode<T> node){
		
		if(node == null)	return new BinaryNode<T>(x);
		
		int compareResult = x.compareTo(node.element);
		
		if(compareResult < 0 )	node.left = insert(x,node.left);
		else if(compareResult > 0 ) node.right = insert(x,node.right);
		
		return  node;
		
	}
	
	public void remove(T x){
		root = remove(x,root);
	}
	
	private BinaryNode<T> remove(T x , BinaryNode<T> node){
		
		if(node == null)	return node;
		int compareResult = x.compareTo(node.element);
		if(compareResult < 0)	node.left = remove(x,node.left);
		else if(compareResult > 0 )	node.right = remove(x,node.right);
		else{
			if(node.left != null && node.right != null){
				node.element = findMin(node.right).element;
				node.right = remove(node.element,node.right);
			}
			else{
				if(node.left == null)	node = node.right;
				else	node = node.left;
			}
		}
		return node;
	}
}
