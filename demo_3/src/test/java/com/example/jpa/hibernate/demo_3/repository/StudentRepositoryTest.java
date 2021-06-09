package com.example.jpa.hibernate.demo_3.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jpa.hibernate.demo_3.Demo3Application;
import com.example.jpa.hibernate.demo_3.entity.Address;
import com.example.jpa.hibernate.demo_3.entity.Course;
import com.example.jpa.hibernate.demo_3.entity.Passport;
import com.example.jpa.hibernate.demo_3.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Demo3Application.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	//Session & Session Factory
	//EntityManager & Persistence Context
	//Transaction
		
	@Test
	public void someTest() {
		repository.someOperationToUnderstandPersistenceContext();
	}

	
	
	@Test
	@Transactional
    public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("passport -> {}",student.getPassport());
	}
	
	@Test
	@Transactional
    public void setAddressDetails() {
		Student student = em.find(Student.class, 20001L);
		student.setAddress(new Address("No 101", "Some Street", "Hyderabad"));
		em.flush();
		logger.info("student -> {}", student);
		logger.info("passport -> {}",student.getPassport());
	}
	
	
	@Test
	@Transactional
    public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {}", passport);
		logger.info("passport -> {}",passport.getStudent());
	}
	
	@Test
	@Transactional
    public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("courses -> {}", student.getCourses());

	}
}
