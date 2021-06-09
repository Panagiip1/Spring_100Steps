package com.example.jpa.hibernate.demo_3.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.jpa.hibernate.demo_3.entity.Course;
import com.example.jpa.hibernate.demo_3.entity.Employee;
import com.example.jpa.hibernate.demo_3.entity.FullTimeEmployee;
import com.example.jpa.hibernate.demo_3.entity.PartTimeEmployee;
import com.example.jpa.hibernate.demo_3.entity.Review;

import java.util.List; 
import java.util.List; 

@Repository
@Transactional
public class EmployeeRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	EntityManager em;
	
	//insert an employee
	public void insert(Employee employee) {
		em.persist(employee);
	}
	
	
	//retrieve all employees
	public List<PartTimeEmployee> retrieveAllPartTimeEmployees() {
		return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class)
				.getResultList();
	}
	
	public List<FullTimeEmployee> retrieveAllFullTimeEmployees() {
		return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class)
				.getResultList();
	}
//	
//	public Course findById(Long id) {
//		return em.find(Course.class, id);
//	}
//	
//	
	
}
