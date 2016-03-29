package com.liuyf.demos.hibernate51.unittest.unidirectional.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.liuyf.demos.hibernate51.pojo.unidirectional.onetoone.AudiencePojo;
import com.liuyf.demos.hibernate51.pojo.unidirectional.onetoone.TicketPojo;

public class UnidirectionalOneToOneExample {

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
			TicketPojo ticket = new TicketPojo();
			ticket.setProgramme("BaiMaoNv");
			ticket.setRow(10);
			ticket.setCol(6);
			
			AudiencePojo audience = new AudiencePojo();
			audience.setTicket(ticket);
			
			session.save(audience);
			
			Assert.assertNotNull(audience.getId());
			Assert.assertNotNull(ticket.getId());
			
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
