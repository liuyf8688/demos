package com.liuyf.demos.javaee.jpa.elementcollection.object.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liuyf.demos.javaee.jpa.elementcollection.object.entity.Computer;

@Repository
public class ComputerDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Computer> list() {
		Query query = entityManager.createQuery("FROM Computer");
		return query.getResultList();
	}
	
	public Computer getById(Long id) {
		return entityManager.find(Computer.class, id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public Computer save(Computer computer) {
		if (computer.getId() == null) {
			entityManager.persist(computer);
		} else {
			entityManager.merge(computer);
		}
		
		return computer;
	}
}
