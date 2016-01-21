package com.liuyf.demos.spring.test.dbunit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.liuyf.demos.spring.test.dbunit.entity.Person;

@Repository
public class PersonDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Person> list(String name) {
		Query query = entityManager.createNamedQuery("Person.list");
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}
}
