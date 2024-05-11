package com.hibernate.learn.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.learn.entity.Passport;
import com.hibernate.learn.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
@Transactional
public class StudentRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager entityManager;
	
	public Student findById(int id) {
		 Student student = entityManager.find(Student.class, id);
		 logger.info("Student Details are: {}", student);
		 logger.info("Student Passport Details are: {}", student.getPassport());
		 return student;
	}
	
	public void findPassportDetailsWithStudentDetails(int id) {
		 Passport passport = entityManager.find(Passport.class, id);
		 logger.info("Passport Details are: {}", passport);
		 logger.info("Student Details with this passport: {}", passport.getStudent());
	}
	
	public void deleteById(int id) {
		Student student = findById(id);
		entityManager.remove(student);
	}
	
	public void save(Student student) {
		entityManager.merge(student);
	}
	
	public void saveWithPassportNumber() {
		Passport passport = new Passport("L34563");
		entityManager.persist(passport);
		Student student1 = new Student("Mike",30);
		student1.setPassport(passport);
		entityManager.persist(student1);
	}
	
	public void learn_query_find() {
		List list = entityManager.createQuery("select c from Student c").getResultList();
		list.forEach(System.out::println);
		
		TypedQuery<Student> query2 = entityManager.createQuery("select c from Student c", Student.class);
		query2.getResultList().forEach(c->logger.info(c.getName()));
		
		TypedQuery<Student> query3 = entityManager.createQuery("select c from Student c where name like '%Hibernate'",Student.class);
		query3.getResultList().forEach(c->logger.info(c.getName()));
	}
	
	public void named_queries() {
		List<Student> list = entityManager.createNamedQuery("query_find_all").getResultList();
		list.forEach(System.out::println);
		
		TypedQuery<Student> query2 = entityManager.createNamedQuery("query_find_all", Student.class);
		query2.getResultList().forEach(c->logger.info(c.getName()));
		
		TypedQuery<Student> query3 = entityManager.createNamedQuery("query_alias_find",Student.class);
		query3.getResultList().forEach(c->logger.info(c.getName()));
	}

}
