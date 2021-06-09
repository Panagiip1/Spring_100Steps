package com.example.jpa.hibernate.demo_3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa.hibernate.demo_3.entity.Course;
import com.example.jpa.hibernate.demo_3.entity.FullTimeEmployee;
import com.example.jpa.hibernate.demo_3.entity.PartTimeEmployee;
import com.example.jpa.hibernate.demo_3.entity.Review;
import com.example.jpa.hibernate.demo_3.entity.Student;
import com.example.jpa.hibernate.demo_3.repository.CourseRepository;
import com.example.jpa.hibernate.demo_3.repository.EmployeeRepository;
import com.example.jpa.hibernate.demo_3.repository.StudentRepository;

@SpringBootApplication
public class Demo3Application implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//studentRepository.saveStudentWithPassport();
		//repository.playWithEntityManager();
		//courseRepository.addHardcodedReviewsForCourse();
		//List<Review> reviews = new ArrayList<>();
		
		//reviews.add(new Review("5", "Great Hands-on Stuff."));
		//reviews.add(new Review("5", "Hatsoff."));
		
		//courseRepository.addReviewsForCourse(10003L, reviews);
		//studentRepository.insertHardcodedStudentAndCourse();
	//	studentRepository.insertStudentAndCourse(new Student("Jack"), new Course("Microservices in 100 Steps"));
	
		//Jack	FullTimeEmployee	salary - 10000$
		//Jill	PartTimeEmployee	50$ per hour
//		employeeRepository.insert(
//				new PartTimeEmployee("Jill", new BigDecimal("50")));
//		
//		employeeRepository.insert(
//				new FullTimeEmployee("Jack", new BigDecimal("10000")));
//		
//		logger.info("All Employees -> {}", employeeRepository.retrieveAllEmployees());
		
//		logger.info("Full Time Employees -> {}", employeeRepository.retrieveAllFullTimeEmployees());
//		
//		logger.info("Part Time Employees -> {}", employeeRepository.retrieveAllPartTimeEmployees());
	}
//		Course course = repository.findById(10001L);
//		logger.info("Course 10001 -> {}", course);
//		repository.save(new Course("Microservices in 100 step"));
//	}
	


}
