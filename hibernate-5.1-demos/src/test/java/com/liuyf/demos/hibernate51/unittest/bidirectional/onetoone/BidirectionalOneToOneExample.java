package com.liuyf.demos.hibernate51.unittest.bidirectional.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.liuyf.demos.hibernate51.pojo.bidirectional.onetoone.GoodsDetailPojo;
import com.liuyf.demos.hibernate51.pojo.bidirectional.onetoone.GoodsPojo;

public class BidirectionalOneToOneExample {

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
			GoodsPojo goods = new GoodsPojo();
			goods.setName("Captain America");
			
			GoodsDetailPojo goodsDetail = new GoodsDetailPojo();
			goodsDetail.setLength(30);
			goodsDetail.setWidth(10);
			goodsDetail.setHeight(100);
			goods.addGoodsDetail(goodsDetail);
			
			session.save(goods);
			
			Assert.assertNotNull(goods.getId());
			Assert.assertNotNull(goodsDetail.getId());
			
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
