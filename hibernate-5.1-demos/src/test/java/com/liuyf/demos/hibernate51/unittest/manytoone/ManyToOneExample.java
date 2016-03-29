package com.liuyf.demos.hibernate51.unittest.manytoone;

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
import com.liuyf.demos.hibernate51.pojo.manytoone.PhonePojo;

public class ManyToOneExample {

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
	public void testSave() {
		
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			PersonPojo person = new PersonPojo();
			person.setName("TonyLiu");
			person.setCreated(LocalDateTime.now());
			
			Serializable personId = session.save(person);
			Assert.assertNotNull(personId);
			
			PhonePojo phone = new PhonePojo();
			phone.setNumber("13800138000");
			phone.setPerson(person);
			Serializable phoneId = session.save(phone);
			Assert.assertNotNull(phoneId);
			Assert.assertNotNull(phone.getPerson());
			
			phone.setPerson(null);
			session.flush();
			PhonePojo out = session.get(PhonePojo.class, phoneId);
			Assert.assertNull(out.getPerson());
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
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
