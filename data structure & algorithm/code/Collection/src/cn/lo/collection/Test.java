package cn.lo.collection;

public class Test {
	
	public static void main(String[] args) {
		int a = 0;
		a = a++;
		System.out.println(a);
	}
	
	private static int test(int num){
		try {
			return 2/num;
		} catch (Exception e) {
			return 0;
		}finally{
			return -1;
		}
	}

}
