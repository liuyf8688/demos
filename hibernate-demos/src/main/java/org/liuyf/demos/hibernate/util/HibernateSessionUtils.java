package org.liuyf.demos.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionUtils {
	
	private static HibernateSessionUtils INSTANCE = new HibernateSessionUtils();

	private SessionFactory sessionFactory = null;
	
	private HibernateSessionUtils() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static HibernateSessionUtils getInstance() {
		return INSTANCE;
	}
	
	public Session openSession() {
		return sessionFactory.openSession();
	}
	
	public void releaseSession(Session session) {
		session.close();
	}
	
	public void shutdown() {
		sessionFactory.close();
	}
	
}
