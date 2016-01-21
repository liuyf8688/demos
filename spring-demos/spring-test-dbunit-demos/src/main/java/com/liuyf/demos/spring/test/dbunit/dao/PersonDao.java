package com.liuyf.demos.spring.test.dbunit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liuyf.demos.spring.test.dbunit.entity.Person;

@Repository
public class PersonDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Person> list(String name) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		String hsql ="SELECT p FROM Person p WHERE 1 = 1";
		if (name != null) {
			hsql += " AND p.firstName like :name or p.lastName like :name";
			params.put("name", "%" + name + "%");
		}
		Query query = entityManager.createQuery(hsql);
		for (Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public Person save(Person person) {
		if (person.getId() == null) {
			entityManager.persist(person);
		} else {
			entityManager.merge(person);
		}
		
		return entityManager.find(Person.class, person.getId());
	}
}
