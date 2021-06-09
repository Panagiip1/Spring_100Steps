package com.example.jpa.hibernate.demo_3.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jpa.hibernate.demo_3.Demo3Application;
import com.example.jpa.hibernate.demo_3.entity.Course;
import com.example.jpa.hibernate.demo_3.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Demo3Application.class)
public class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void all_courses() {
		//"Select c From Course c"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the
		// expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		
		// 4. Add Predicates etc to the Criteria Query
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		java.util.List<Course> resultList = query.getResultList();
		
		logger.info("Typed Query -> {}", resultList);
		//2021-05-07 11:44:11.076  INFO 21800 --- [           main] c.e.j.h.d.repository.CriteriaQueryTest   : Typed Query -> [Course[JPA in 50 Steps], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]]
	}

	
	@Test
	public void all_courses_having_1000Steps() {
		//"Select c From Course c where name like '%100 Steps'"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the
		// expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");
		
		// 4. Add Predicates etc to the Criteria Query
		cq.where(like100Steps);
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		java.util.List<Course> resultList = query.getResultList();
		
		logger.info("Typed Query -> {}", resultList);
		//2021-05-09 19:57:55.688  INFO 24465 --- [           main] c.e.j.h.d.repository.CriteriaQueryTest   : Typed Query -> [Course[Spring Boot in 100 Steps]]
	}
	
	@Test
	public void all_courses_without_students() {
		//"Select c From Course c where c.students is empty"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the
		// expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		
		// 4. Add Predicates etc to the Criteria Query
		cq.where(studentsIsEmpty);
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		java.util.List<Course> resultList = query.getResultList();
		
		logger.info("Typed Query -> {}", resultList);
		//
	}
	
	@Test
	public void join() {
		//"Select c From Course c join c.students s"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the
		// expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students");
		
		// 4. Add Predicates etc to the Criteria Query
		
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		java.util.List<Course> resultList = query.getResultList();
		
		logger.info("Typed Query -> {}", resultList);
		//[Course[JPA in 50 Steps], Course[JPA in 50 Steps], Course[JPA in 50 Steps], Course[Spring Boot in 100 Steps]]
	}
	
	@Test
	public void left_join() {
		//"Select c From Course c left join c.students s"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the
		// expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		
		// 4. Add Predicates etc to the Criteria Query
		
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		java.util.List<Course> resultList = query.getResultList();
		
		logger.info("Typed Query -> {}", resultList);
		//[Course[JPA in 50 Steps], Course[JPA in 50 Steps], Course[JPA in 50 Steps], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]]
	}
	
}
