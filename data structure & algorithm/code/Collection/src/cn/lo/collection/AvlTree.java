package cn.lo.collection;

import java.text.SimpleDateFormat;


public class AvlTree<T extends Comparable<? super T>> {
	
	private static class AvlNode<T>{
		private T element;
		private AvlNode<T> left;
		private AvlNode<T> right;
		private int height;
	
		public AvlNode(T element){
			this(element,null,null);
		}
		
		public AvlNode (T element, AvlNode<T> left, AvlNode<T> right) {
			this.element = element;
			this.left = left;
			this.right = right;
			this.height = 0;
		}
		
	}
	
	private AvlNode<T> root;
	
	private int height(AvlNode<?> node){
		return node == null ? -1 : node.height;
	}
	
	private AvlNode<T> insert(T x,AvlNode<T> node){
		
		if(node == null){
			return new AvlNode<T>(x);
		}
		int res = x.compareTo(node.element);
		if(res < 0){
			node.left = insert(x,node.left);
			if((height(node.left) - height(node.right)) == 2){
				if(x.compareTo(node.left.element) < 0){
					node = rotateWithLeftChild(node);
				}else{
					node = doubleRotateWithLeftChild(node);
				}
			}
		}else if(res > 0){
			node.right = insert(x,node.right);
			if(height(node.right) - height(node.left) == 2){
				if(x.compareTo(node.right.element) > 0){
					node = rotateWithRightChild(node);
				}else{
					node = doubleRotateWithRightChild(node);
				}
			}
		}else{
			
		}
		
		node.height = Math.max(height(node.left),height(node.right)) + 1;
		
		return node;
	}
	
	private AvlNode<T> rotateWithLeftChild(AvlNode<T> node){
		
		AvlNode<T> newNode = node.left;
		node.left = node.left.right;
		newNode.right = node;
		return newNode;
	}
	
	private AvlNode<T> rotateWithRightChild(AvlNode<T> node){
		AvlNode<T> newNode = node.right;
		node.right = newNode.left;
		newNode.left = node;
		return newNode;
	}
	
	private AvlNode<T> doubleRotateWithLeftChild(AvlNode<T> node){
		node.left = rotateWithRightChild(node.left);
		return rotateWithLeftChild(node);
	}
	
	private AvlNode<T> doubleRotateWithRightChild(AvlNode<T> node){
		node.right = rotateWithRightChild(node.right);
		return rotateWithLeftChild(node);
		
	}
}
