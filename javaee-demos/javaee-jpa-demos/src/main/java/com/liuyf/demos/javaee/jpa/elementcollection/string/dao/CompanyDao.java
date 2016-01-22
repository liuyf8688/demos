package com.liuyf.demos.javaee.jpa.elementcollection.string.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.liuyf.demos.javaee.jpa.elementcollection.string.entity.Company;

@Repository
public class CompanyDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Company> list() {
		Query query = entityManager.createQuery("FROM Company");
		return query.getResultList();
	}
	
	public Company getById(Long id) {
		return entityManager.find(Company.class, id);
	}
}
