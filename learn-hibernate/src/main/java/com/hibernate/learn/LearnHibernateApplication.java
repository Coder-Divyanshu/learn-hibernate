package com.hibernate.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hibernate.learn.entity.Course;
import com.hibernate.learn.repository.CourseRepository;
import com.hibernate.learn.repository.StudentRepository;

@SpringBootApplication
public class LearnHibernateApplication implements CommandLineRunner{

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(LearnHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Course course = courseRepository.findById(101L);
//		logger.info("Course Details finding by Id: {}", course);
//		
//		courseRepository.deleteById(102L);	
//		
//		courseRepository.save(new Course("Learn AWS"));
//		courseRepository.save(new Course(101L,"Learn Hibernate with me"));
//		courseRepository.playWithEntityManager();
//		courseRepository.learn_query_find();
//		courseRepository.named_queries();
		
		studentRepository.saveWithPassportNumber();
		studentRepository.findById(201);
		
		studentRepository.findPassportDetailsWithStudentDetails(301);
		 	
	}

}
