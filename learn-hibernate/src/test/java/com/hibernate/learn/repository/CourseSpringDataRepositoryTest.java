package com.hibernate.learn.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;

import com.hibernate.learn.entity.Course;

@SpringBootTest
public class CourseSpringDataRepositoryTest {
	

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseSpringDataRepository courseSpringDataRepository;
	
	@Test
	public void isCoursePresent() {
		Optional<Course> course = courseSpringDataRepository.findById(101L);
	}
	
	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 2);
		Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);
		Pageable nextPageable = firstPage.nextPageable();
		Page<Course> secondPage = courseSpringDataRepository.findAll(nextPageable);
		logger.info("{}",firstPage.getContent());
		logger.info("{}",secondPage.getContent());
	}
	
	@Test
	public void customQuery() {
//		Course findingByName = courseSpringDataRepository.findByName("Learn Mokcito");
//		logger.info("{}",findingByName);
	}
}
