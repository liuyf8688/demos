package com.liuyf.demos.cache.ehcache2;

import com.liuyf.demos.cache.ehcache2.pojo.Person;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class VerifyEhcacheIsNotDeepCopy {
	
	private static final String PERSON = "PERSON_";

	public static void main(String[] args) {
		
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
			
			System.out.println(" === before putting cache, " + person.toString());
			
			final Element element = new Element(key, person);
			cache.put(element);
			
			person.setName("LiuYanfeng");
			person.setAge(19);
			
			Element e = cache.get(key);
			Person p = (Person)e.getObjectValue();
			
			System.out.println(" === get from cache, " + p.toString());
		} finally {
			cacheManager.shutdown();
		}
	}
}
