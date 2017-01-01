package cn.lo.serial;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee extends SerialCloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double salary;
	private Date hireDay;
	public Employee(String name, double salary, int year ,int month , int day) {
		super();
		this.name = name;
		this.salary = salary;
		this.hireDay = new GregorianCalendar(year, month-1, day).getTime();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getHireDay() {
		return hireDay;
	}
	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	
	public String toString(){
		return (this.getClass().getName() + "   " + name + "   " + salary + "    " +  hireDay);
	}
	
}
