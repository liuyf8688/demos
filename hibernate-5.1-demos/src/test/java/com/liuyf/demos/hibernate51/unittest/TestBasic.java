package com.liuyf.demos.hibernate51.unittest;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.liuyf.demos.hibernate51.pojo.manytoone.PersonPojo;

public class TestBasic {

	private SessionFactory sessionFactory = null;
	
	@Before
	public void setUp() { 
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
		Assert.assertNotNull(sessionFactory);
	}
	
	@Test
	public void testSavePerson() {
		
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			PersonPojo pojo = new PersonPojo();
			pojo.setName("TonyLiu");
			pojo.setCreated(LocalDateTime.now());
			
			Serializable id = session.save(pojo);
			Assert.assertNotNull(id);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().commit();
				session.close();
			}
			Assert.fail(e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
