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
	
	public static void main(String[] args) {
		permutation("abcd");
	}

}
