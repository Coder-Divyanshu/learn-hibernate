package com.hibernate.learn.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries(value = {
		@NamedQuery(name="query_find_all",query="select c from Course c"),
		@NamedQuery(name="query_alias_find",query="select c from Course c where name like '%me'")
})
public class Course {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	@CreationTimestamp
	private LocalDateTime creationDate;
	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<Review>();
	
	public Course() {
		
	}
	
	public Course(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
}
