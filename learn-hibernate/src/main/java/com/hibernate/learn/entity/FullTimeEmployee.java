package com.hibernate.learn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity
public class FullTimeEmployee extends Employee{
	
	private int salary;

	public FullTimeEmployee() {
		
	}

	public FullTimeEmployee(String name, int salary) {
		super(name);
		this.salary=salary;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "FullTimeEmployee [name=" + super.getName() + "]";
	}
	
	
}
