package cn.lo.collection;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;


public class Infix2Suffix {
	
	private static HashMap<Character, Integer> priorityMap;
	
	static{
		priorityMap = new HashMap<Character, Integer>();
		
		priorityMap.put('+', 0);
		priorityMap.put('-', 0);
		priorityMap.put('*', 1);
		priorityMap.put('/', 1);
		priorityMap.put('(', 2);
		priorityMap.put(')', -1);
	}
	public static String makeInfix2Suffix(String infixStr){
		
		String suffixStr = new String();
		
		Deque<Character> opQueue = new LinkedList<Character>();
		
		char[] infixCharArray = infixStr.replace(" ", "").toCharArray();
		
		for(char x : infixCharArray){
			if(isOperatorSymbol(x)){
				while((!opQueue.isEmpty()) && (priorityMap.get(opQueue.peekLast()) >= priorityMap.get(x))){
					if(opQueue.peekLast() == '('){
						if(x == ')')	opQueue.pollLast();
						break;
					}
					suffixStr += opQueue.pollLast();
				}
				if(x != ')')	opQueue.add(x);
			}
			else
				suffixStr += x;
		}
		
		while(!opQueue.isEmpty()){
			suffixStr += opQueue.pollLast();
		}
		
		return suffixStr;
		
	}
	
	private static boolean isOperatorSymbol(char x){
		return x == '+' || x == '-' || x == '*' || x == '/' || x == '(' || x == ')';
	}
	
	public static void main(String[] args) {
		String infixStr = "a+b*(c+d*e+f)*g";
		System.out.println(makeInfix2Suffix(infixStr));
	}

}
