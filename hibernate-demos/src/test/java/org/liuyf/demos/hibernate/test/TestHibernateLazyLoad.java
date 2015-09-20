package org.liuyf.demos.hibernate.test;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.liuyf.demos.hibernate.pojo.Address;
import org.liuyf.demos.hibernate.pojo.Person;

public class TestHibernateLazyLoad {

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws HibernateException, MalformedURLException {
		
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = new Configuration()
					.configure()
					.buildSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			Query query = session.createQuery("delete from Person");
			query.executeUpdate();
			
			Address address1 = new Address();
			address1.setCity("Beijing");
			address1.setStreet("Road GuangHua");
			
			Address address2 = new Address();
			address2.setCity("Beijing");
			address2.setStreet("Road SanLiTun");
			
			Person person1 = new Person();
			person1.setName("LiuYanfeng");
			Set<Address> addresses1 = new HashSet<>();
			addresses1.add(address1);
			person1.setReceiveAddresses(addresses1);
			
			Person person2 = new Person();
			person2.setName("LiuYanfeng");
			Set<Address> addresses2 = new HashSet<>();
			addresses2.add(address2);
			person2.setReceiveAddresses(addresses2);
			
			System.out.println("Id: " + session.save(person1));
			System.out.println("Id: " + session.save(person2));
			
			
			query = session.createQuery("from Person");
			@SuppressWarnings("unchecked")
			List<Person> persons = query.list();
			for (Person person : persons) {
				System.out.println("Name: " + person.getName() + ", address, " + person.getReceiveAddresses().toArray(new Address[0])[0].getCity());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}
}
