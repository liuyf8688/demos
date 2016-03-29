package com.liuyf.demos.hibernate51.unittest.unidirectional.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.liuyf.demos.hibernate51.pojo.unidirectional.onetomany.BookPojo;
import com.liuyf.demos.hibernate51.pojo.unidirectional.onetomany.WriterPojo;

public class UnidirectionalOneToManyExample {

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
			WriterPojo writer = new WriterPojo();
			
			BookPojo book1 = new BookPojo();
			book1.setName("Java");
			BookPojo book2 = new BookPojo();
			book2.setName("Javascript");
			
			writer.getBooks().add(book1);
			writer.getBooks().add(book2);
			session.save(writer);
			session.flush();
			
			Assert.assertEquals(2, writer.getBooks().size());
			
			writer.getBooks().remove(book1);
			
			Assert.assertEquals(1, writer.getBooks().size());
			
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
