package com.hibernate.learn.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import com.hibernate.learn.entity.Course;

import jakarta.transaction.Transactional;

@SpringBootTest
public class CourseRepositoryTest {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	public void test_findByID() {
		Course course = courseRepository.findById(101L);
		assertEquals("Learn Hibernate with me", course.getName());
	}
	
	@Test
	@Transactional
	public void test_firstLevelCaching() {
		Course course = courseRepository.findById(101L);
		logger.info("First Time Fetching Data");
		
		Course course1 = courseRepository.findById(101L);
		logger.info("Second Time Fetching Data");
		
		assertEquals("Learn Hibernate", course.getName());
		assertEquals("Learn Hibernate", course1.getName());
	}
	
	@Test
	@DirtiesContext
	public void test_deleteById() {
		courseRepository.deleteById(101L);
		assertNull(courseRepository.findById(101L));
	}
	
	@Test
	@DirtiesContext
	public void test_save_insert() {
		Course course = new Course(107L,"Learn Something new");
		courseRepository.save(course);
		assertEquals("Learn Something new", course.getName());
	}
	
	@Test
	@DirtiesContext
	public void test_save_update() {
		Course course = new Course(101L,"Learn Something new");
		courseRepository.save(course);
		assertEquals("Learn Something new", course.getName());
	}
}
