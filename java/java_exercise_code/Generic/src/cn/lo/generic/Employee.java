package cn.lo.generic;

public class Employee {
	
	private final String name;
	private double salary = 0;
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}
	
	
}
