package com.hibernate.learn.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.learn.entity.Course;
import com.hibernate.learn.entity.Review;
import com.hibernate.learn.entity.ReviewRating;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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
	
	public void insertReviewsForCourses() {
		
		Course course = findById(104L);
		Review review1 = new Review(ReviewRating.TWO,"Not so good");
		Review review2 = new Review(ReviewRating.ONE,"Bad");
		
		review1.setCourse(course);
		review2.setCourse(course);
		
		entityManager.persist(review1);
		entityManager.persist(review2);
	}
	
	public void retrieveReviewAnsAssociatedCourse() {
		Review review = entityManager.find(Review.class, 1);
		logger.info("{}",review.getCourse());
	}
	
	public void jpql_query() {
		List<Course> resultList = entityManager.createQuery("Select c from Course c where c.students is empty", Course.class).getResultList();
		logger.info("Courses which are without students are: "+ resultList);
	}
	
	public void jpql_query1() {
		List<Course> resultList = entityManager.createQuery("Select c from Course c order by size(c.students) desc", Course.class).getResultList();
		logger.info("Courses which are without students are: "+ resultList);
	}
	
	public void jpql_query2() {
		List<Course> resultList = entityManager.createQuery("Select c from Course c where size(c.students)>2", Course.class).getResultList();
		logger.info("Courses which are without students are: "+ resultList);
	}
	public void jpql_query4() {
		List<Course> resultList = entityManager.createQuery("Select c from Course c where name like '%Learn%'", Course.class).getResultList();
		logger.info("Courses which are without students are: "+ resultList);
	}
	
	public void jpql_query5() {
		List<Course> resultList = entityManager.createQuery("Select upper(c.name) from Course c", Course.class).getResultList();
		logger.info("Courses which are without students are: "+ resultList);
	}
	
	public void criteria_query() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> root = criteriaQuery.from(Course.class);
		List<Course> resultList = entityManager.createQuery(criteriaQuery.select(root)).getResultList();
		logger.info("Result Set : {}", resultList);
	}

}
