package com.liuyf.demos.querydsl.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.liuyf.demos.querydsl.jpa.pojo.PersonPojo;
import com.liuyf.demos.querydsl.jpa.pojo.QGradePojo;
import com.liuyf.demos.querydsl.jpa.pojo.QPersonPojo;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class TestJPAQueryDsl {

	private EntityManagerFactory emf = null;
	private EntityManager em = null;

	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("pu");
		em = emf.createEntityManager();
	}
	
	@Test
	public void testQuerying() {
		QPersonPojo qPerson = QPersonPojo.personPojo;
		JPAQueryFactory factory = new JPAQueryFactory(em);
		PersonPojo pojo = factory.selectFrom(qPerson)
				.where(qPerson.name.eq("abc"))
				.fetchOne();
		
		Assert.assertNull(pojo);
	}
	
	@Test
	public void testMultiTables() {
		QPersonPojo qPerson = QPersonPojo.personPojo;
		QGradePojo qGrade = QGradePojo.gradePojo;
		JPAQueryFactory factory = new JPAQueryFactory(em);
		factory.from(qPerson, qGrade)
			.fetch();
	}
	
	@Test
	public void testJoin() {
		QPersonPojo qPerson = QPersonPojo.personPojo;
		QGradePojo qGrade = QGradePojo.gradePojo;
		JPAQueryFactory factory = new JPAQueryFactory(em);
		factory.selectFrom(qPerson)
			.join(qGrade)
			.on(qPerson.id.gt(10))
			.fetchOne();
	}
	
	@Test
	public void testOrderBy() {
		QPersonPojo qPerson = QPersonPojo.personPojo;
		JPAQueryFactory factory = new JPAQueryFactory(em);
		factory.selectFrom(qPerson)
			.orderBy(qPerson.name.asc())
			.fetch();
	}
	
	@Test
	public void testGroupBy() {
		QPersonPojo qPerson = QPersonPojo.personPojo;
		JPAQueryFactory factory = new JPAQueryFactory(em);
		factory.select(qPerson.name)
			.from(qPerson)
			.groupBy(qPerson.name)
			.fetch();
	}
	
	@Test
	public void testUpdate() {
		em.getTransaction().begin();
		try {
			QPersonPojo qPerson = QPersonPojo.personPojo;
			JPAQueryFactory factory = new JPAQueryFactory(em);
			factory.update(qPerson)
				.set(qPerson.name, "abc123")
				.where(qPerson.id.eq(1L))
				.execute();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
	
	@Test
	public void testDelete() {
		em.getTransaction().begin();
		try {
			QPersonPojo qPerson = QPersonPojo.personPojo;
			JPAQueryFactory factory = new JPAQueryFactory(em);
			factory.delete(qPerson)
				.execute();
			factory.delete(qPerson)
				.where(qPerson.name.eq("abc"))
				.execute();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
	
	@Test
	public void testExposeOriginalQuery() {
		QPersonPojo qPerson = QPersonPojo.personPojo;
		JPAQueryFactory factory = new JPAQueryFactory(em);
		factory.selectFrom(qPerson)
			.createQuery()
			.getResultList();
	}
	
	@After
	public void tearDown() {
		if (em != null) {
			em.clear();
		}
		if (emf != null) {
			emf.close();
		}
	}
}
