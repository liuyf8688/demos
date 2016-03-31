package com.liuyf.demos.querydsl.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.liuyf.demos.querydsl.jpa.pojo.PersonPojo;

public class TestQueryDsl {
	
	private EntityManagerFactory emf = null;

	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("pu");
	}

	@Test
	public void test() {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			PersonPojo pojo = new PersonPojo();
			em.persist(pojo);
			transaction.commit();
			
			Assert.assertNotNull(pojo.getId());
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		
	}
	
	@After
	public void tearDown() {
		if (emf != null) {
			emf.close();
		}
	}

}
