package cn.lo.generic;

public class Manager extends Employee{

	private double bonus = 0;
	
	public Manager(String name, double salary) {
		super(name, salary);
		// TODO Auto-generated constructor stub
	}
	

	public Manager(String name, double salary, double bonus) {
		super(name, salary);
		this.bonus = bonus;
	}



	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	
	
}
