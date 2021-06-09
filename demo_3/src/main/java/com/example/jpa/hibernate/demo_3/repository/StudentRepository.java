package com.example.jpa.hibernate.demo_3.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.jpa.hibernate.demo_3.entity.Course;
import com.example.jpa.hibernate.demo_3.entity.Passport;
import com.example.jpa.hibernate.demo_3.entity.Student;


@Repository
@Transactional
public class StudentRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public Student save(Student student) {
		
	    if(student.getId()==null) {
			em.persist(student);
		} else {
			em.merge(student);
		}
		
		return student;
	}
	
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		
		Student student = new Student("Mike");
		
		student.setPassport(passport);
		em.persist(student);

	}
	
	public void someOperationToUnderstandPersistenceContext() {
		//Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);
		// Persistence Context (student)
		
		//Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();
		// Persistence Context (student, passport)
		
		//Database Operation 3 - update student
		passport.setNumber("E123457");
		//// Persistence Context (student, passport++)
		
		//Database Operation 4 - update passport
		student.setName("Ranga - updated");
		// // Persistence Context (student++, passport++)
	}
	
	public void insertStudentAndCourse(Student student, Course course) {
		//Student student = new Student("Jack");
		//Course course = new Course("Microservices in 100 Steps");
		student.addCourses(course);
		course.addStudents(student);
		
		em.persist(student);
		em.persist(course);

		
	}
}
