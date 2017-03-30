package cn.lo.collection;


public class BuildTree {
	
	private static class Node{
		private Node left;
		private Node right;
		private int element;
		public int getElement() {
			return element;
		}
		public void setElement(int element) {
			this.element = element;
		}
		public Node(Node left, Node right, int element) {
			this.left = left;
			this.right = right;
			this.element = element;
		}
		public Node(int element){
			this(null,null,element);
		}
		public Node getLeft() {
			return left;
		}
		public Node getRight() {
			return right;
		}
		public void setLeft(Node left) {
			this.left = left;
		}
		public void setRight(Node right) {
			this.right = right;
		}
	}
	
	public static Node buildTreeFromExpression(int[] prefix, int[] infix) throws Exception{
		
		
		int length = prefix.length;
		return constructRoot(prefix,infix,0,0,length -1);
	}
	
	private static Node constructRoot(int[] prefix , int[] infix,int rootIdxForPrefix, int startIdxForInfix, int endIdxForInfix) throws Exception{
		
		if(startIdxForInfix > endIdxForInfix){
			return null;
		}
		
		Node rootNode = new Node(prefix[rootIdxForPrefix]);
		
		if(startIdxForInfix == endIdxForInfix){
			return rootNode;
		}
		
		int rootIdxForInfix = getRootIdxForInfix(infix,prefix[rootIdxForPrefix],startIdxForInfix,endIdxForInfix);
		int leftStartIdxForInfix = startIdxForInfix;
		int leftEndIdxForInfix = rootIdxForInfix - 1;
		int rightStartIdxForInfix = rootIdxForInfix + 1;
		int rightEndIdxForInfix = endIdxForInfix ;
		
		int leftRootIdxForPrefix = rootIdxForPrefix + 1;
		int rightRootIdxForPrefix = rootIdxForPrefix + leftEndIdxForInfix - leftStartIdxForInfix + 2;
		
		rootNode.setLeft(constructRoot(prefix,infix,leftRootIdxForPrefix,leftStartIdxForInfix,leftEndIdxForInfix));
		rootNode.setRight(constructRoot(prefix,infix,rightRootIdxForPrefix,rightStartIdxForInfix,rightEndIdxForInfix));
		
		return rootNode;
	}
	
	private static int getRootIdxForInfix(int[] infix , int rootVal , int startIdxForInfix,int endIdxForInfix) throws Exception{
		for(int i = startIdxForInfix ; i <= endIdxForInfix; i ++ ){
			if(infix[i] == rootVal){
				return i;
			}
		}
		
		throw new Exception("Can not find the root value");
		
	}
	
	public static void main(String[] args) throws Exception {
		int[] prefix = {1,2,4,7,3,5,6,8};
		int[] infix = {4,7,2,1,5,3,8,6};
		
		Node node = buildTreeFromExpression(prefix,infix);
		
		sufixTravle(node);
		
		System.out.println("-----------------------");
		
		infixTravle(node);
	}
	
	private static void sufixTravle(Node node){
		System.out.println(node.getElement());
		Node leftNode = node.getLeft();
		if(leftNode != null)
			sufixTravle(leftNode);
		Node rightNode = node.getRight();
		if(rightNode != null)
			sufixTravle(node.getRight());
	}
	
	private static void infixTravle(Node node){
		if(node.left != null){
			infixTravle(node.left);
		}
		
		System.out.println(node.element);
		
		if(node.right != null){
			infixTravle(node.right);
		}
	}

}
