package com.hibernate.learn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity
public class PartTimeEmployee extends Employee{
	
	private int hourlyWages;

	public PartTimeEmployee() {
		
	}

	public PartTimeEmployee(String name, int hourlyWages) {
		super(name);
		this.hourlyWages=hourlyWages;
	}

	public int getHourlyWages() {
		return hourlyWages;
	}

	public void setHourlyWages(int hourlyWages) {
		this.hourlyWages = hourlyWages;
	}

	@Override
	public String toString() {
		return "PartTimeEMployee [name=" + super.getName() + "]";
	}
	
	
}
