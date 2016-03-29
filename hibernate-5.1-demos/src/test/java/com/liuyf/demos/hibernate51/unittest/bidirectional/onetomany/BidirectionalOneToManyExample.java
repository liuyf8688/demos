package com.liuyf.demos.hibernate51.unittest.bidirectional.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.liuyf.demos.hibernate51.pojo.bidirectional.onetomany.CarPojo;
import com.liuyf.demos.hibernate51.pojo.bidirectional.onetomany.PartPojo;

public class BidirectionalOneToManyExample {

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
			CarPojo car = new CarPojo();
			
			PartPojo tire = new PartPojo();
			tire.setName("Tire");
			tire.setFactory("Michelin");
			PartPojo frontGlass = new PartPojo();
			frontGlass.setName("Front Glass");
			frontGlass.setFactory("AGC");
			
			car.addPart(tire);
			car.addPart(frontGlass);
			
			session.save(car);
			session.flush();
			
			car.removePart(tire);
			
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
