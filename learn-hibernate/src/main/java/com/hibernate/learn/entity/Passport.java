package com.hibernate.learn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Passport {
	
	@Id
	@GeneratedValue
	private int passport_id;
	private String number;
	@OneToOne(mappedBy = "passport")
	private Student student;
	
	public Passport() {
		
	}

	public Passport(String number) {
		this.number=number;
	}
	public int getPassport_id() {
		return passport_id;
	}

	public void setPassport_id(int passport_id) {
		this.passport_id = passport_id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Passport [number=" + number + "]";
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
