package cn.lo.serial;

public class SerialDemo {
	public static void main(String[] args) {
		Employee e1 = new Employee("jack", 1000, 1995, 11, 2);
		
		Employee e2 = (Employee)e1.clone();
		
		System.out.println(e1);
		
		System.out.println(e2);
	}
}
