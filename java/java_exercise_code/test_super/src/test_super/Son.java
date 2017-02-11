package test_super;

public class Son extends Father{
	@Override
	public void m() {
		// TODO Auto-generated method stub
		super.m();
	}
	public static void main(String[] args) {
		Son son = new Son();
		son.m();
	}
}
