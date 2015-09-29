package org.liuyf.demos.hibernate.mapping.collection.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.liuyf.demos.hibernate.mapping.collection.pojo.UserInfo;
import org.liuyf.demos.hibernate.util.HibernateSessionUtils;

public class ElementCollectionTest {

	private Session session;
	
	@Before
	public void setUp() {
		session = HibernateSessionUtils.getInstance().openSession();
	}
	
	@After
	public void tearDown() {
		
		if (session != null) {
			session.close();
		}
		
		HibernateSessionUtils.getInstance().shutdown();
	}
	
	@Test
	public void testElementCollection () {
		
		Transaction transaction = session.beginTransaction();
		try {
			UserInfo info = new UserInfo();
			info.setFirstName("Yanfeng");
			info.setLastName("Liu");
			
			List<String> nicknames = new ArrayList<>();
			nicknames.add("Tony");
			nicknames.add("Yefeng");
			
			info.setNicknames(nicknames);
			
			Long id = (Long) session.save(info);
			
			transaction.commit();
			
			Assert.assertNotNull(id);
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		
	}
}
