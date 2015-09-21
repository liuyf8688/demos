package org.liuyf.demos.hibernate.test;
import java.net.MalformedURLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestHibernateLazyLoad {

	private SessionFactory sessionFactory = null;
	
	private Session session = null;
	
	@Before
	public void setUp() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.getCurrentSession();
	}
	
	@After
	public void tearDown() {
		
		// session.close() //Transaction ended, Session will automatically be closed
		
		if ((sessionFactory != null) || (!sessionFactory.isClosed())) {
			sessionFactory.close();
		}
	}

	@Test(expected = SessionException.class)
	public void testTransactionEndedThenTryToAccessLazyLoadFieldExpectedSessionException() throws HibernateException, MalformedURLException {
		
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
		}
	}
}
