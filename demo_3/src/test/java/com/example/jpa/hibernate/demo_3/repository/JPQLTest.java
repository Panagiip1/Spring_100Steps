package com.example.jpa.hibernate.demo_3.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_basic() {
		Query query = em.createNamedQuery("query_get_all_courses");
		java.util.List resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpql_typed() {
		TypedQuery<Course> query =
				em.createNamedQuery("query_get_all_courses", Course.class);
		
		java.util.List<Course> resultList = query.getResultList();
		
		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpql_where() {
		TypedQuery<Course> query =
				em.createNamedQuery("query_get_100_Step_courses", Course.class);	
		java.util.List<Course> resultList = query.getResultList();
		
		logger.info("Select c From Course c where name like '%100 Steps'-> {}", resultList);
	}

	@Test
	public void jpql_courses_without_student() {
		TypedQuery<Course> query =
				em.createQuery("Select c from Course c where c.students is empty", 
						Course.class);	
		java.util.List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	
	@Test
	public void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> query =
				em.createQuery("Select c from Course c where size(c.students) >= 2", 
						Course.class);	
		java.util.List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void jpql_courses_ordered_by_students() {
		TypedQuery<Course> query =
				em.createQuery("Select c from Course c order by size(c.students) desc", 
						Course.class);	
		java.util.List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	
	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query =
				em.createQuery("Select s from Student s where s.passport.number like '%1234%'", 
						Student.class);	
		java.util.List<Student> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	
	//like
	//BETWEEN 100 and 1000
	//IS NULL
	//upper, lower, trim, length
	
	//JOIN => Select c, s from Course c JOIN c.students s
	//LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
	//CROSS JOIN => Select c, s from Course c, Student s
	//3 and 4 =>3 * 4 = 12 Rows
	@Test
	public void join() {
		Query query =
				em.createQuery("Select c, s from Course c JOIN c.students s");
		java.util.List<Object[]> resultList = query.getResultList();
		logger.info("Results -> {}", resultList.size());
		for(Object[] result:resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	@Test
	public void left_join() {
		Query query =
				em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		java.util.List<Object[]> resultList = query.getResultList();
		logger.info("Results -> {}", resultList.size());
		for(Object[] result:resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	@Test
	public void cross_join() {
		Query query =
				em.createQuery("Select c, s from Course c, Students s");
		java.util.List<Object[]> resultList = query.getResultList();
		logger.info("Results -> {}", resultList.size());
		for(Object[] result:resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
}
