package test_final;

public class Test {
	public static void main(String[] args) {

		System.out.println(test(0));
		System.out.println(test(1));
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
