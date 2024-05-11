package com.hibernate.learn.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.learn.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
@Transactional
public class CourseRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager entityManager;
	
	public Course findById(Long id) {
		return entityManager.find(Course.class, id);
	}
	
	public void deleteById(Long id) {
		Course course = findById(id);
		entityManager.remove(course);
	}
	
	public void save(Course course) {
		entityManager.merge(course);
	}
	
	public void playWithEntityManager() {
		Course course1 = new Course("Learn React");
		entityManager.persist(course1);
		entityManager.flush();
		//entityManager.clear();
		course1.setName("Learn React - Updated");
		entityManager.refresh(course1);
	}
	
	public void learn_query_find() {
		List list = entityManager.createQuery("select c from Course c").getResultList();
		list.forEach(System.out::println);
		
		TypedQuery<Course> query2 = entityManager.createQuery("select c from Course c", Course.class);
		query2.getResultList().forEach(c->logger.info(c.getName()));
		
		TypedQuery<Course> query3 = entityManager.createQuery("select c from Course c where name like '%Hibernate'",Course.class);
		query3.getResultList().forEach(c->logger.info(c.getName()));
	}
	
	public void named_queries() {
		List<Course> list = entityManager.createNamedQuery("query_find_all").getResultList();
		list.forEach(System.out::println);
		
		TypedQuery<Course> query2 = entityManager.createNamedQuery("query_find_all", Course.class);
		query2.getResultList().forEach(c->logger.info(c.getName()));
		
		TypedQuery<Course> query3 = entityManager.createNamedQuery("query_alias_find",Course.class);
		query3.getResultList().forEach(c->logger.info(c.getName()));
	}

}
