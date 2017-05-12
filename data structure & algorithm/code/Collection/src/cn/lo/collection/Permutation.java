package cn.lo.collection;

public class Permutation {
	
	public static void permutation(String s){
		char[] cArr = s.toCharArray();
		permutation(cArr,0);
	}
	
	private static void permutation(char[] cArr,int startIdx){
		if(startIdx == cArr.length-1 ){
			 System.out.println(cArr);
			 return;
		}
		
		for(int i = startIdx; i <= cArr.length - 1 ; i++){
			char temp = cArr[i];
			cArr[i] = cArr[startIdx];
			cArr[startIdx] = temp;
			
			permutation(cArr,startIdx + 1);
			
			temp = cArr[i];
			cArr[i] = cArr[startIdx];
			cArr[startIdx] = temp;
		}
	}
	
	public static void printToMaxOfNDigits(int n){
		char[] cArr = new char[n];
		printToMaxNDigitsRecursively(cArr,n,0);
	}
	
	private static void printToMaxNDigitsRecursively(char[] cArr,int n, int pos){
		if(pos == n){
			System.out.print(cArr);
			System.out.print(" ");
			return;
		}
		for(int i = 0; i < 10 ; i++){
			cArr[pos] = (char) ('0' + i);
			printToMaxNDigitsRecursively(cArr,n,pos+1);
		}
	}
	
	public static void main(String[] args) {
		printToMaxOfNDigits(2);
	}
	


}
