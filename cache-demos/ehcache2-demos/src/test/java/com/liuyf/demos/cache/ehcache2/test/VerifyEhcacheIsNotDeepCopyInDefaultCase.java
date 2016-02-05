package com.liuyf.demos.cache.ehcache2.test;

import org.junit.Assert;
import org.junit.Test;

import com.liuyf.demos.cache.ehcache2.pojo.Person;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class VerifyEhcacheIsNotDeepCopyInDefaultCase {
	
	private static final String PERSON = "PERSON_";

	@Test
	public void test() {
		
		final CacheManager cacheManager = new CacheManager();
		try {
		
			final Ehcache cache = cacheManager.getEhcache("hello-world");
			
			System.out.println(cache);
			
			final Person person = new Person();
			person.setId(100L);
			person.setName("TonyLiu");
			person.setAge(16);
			//
			String key = PERSON + person.getId();
			String snapshot = person.toString();
			System.out.println(" === before putting cache, " + snapshot);
			
			final Element element = new Element(key, person);
			cache.put(element);
			
			person.setName("LiuYanfeng");
			person.setAge(19);
			
			Element e = cache.get(key);
			Person p = (Person)e.getObjectValue();
			
			String actual = p.toString();
			System.out.println(" === get from cache, " + actual);
			
			Assert.assertNotEquals(snapshot, actual);
		} finally {
			cacheManager.shutdown();
		}
	}
}
