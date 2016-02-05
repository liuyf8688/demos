package com.liuyf.demos.cache.ehcache2;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class Ehcache2KickOff {

	public static void main(String[] args) {
		
		final CacheManager cacheManager = new CacheManager();
		try {
			
			final Ehcache cache = cacheManager.getEhcache("hello-world");
			
			System.out.println(cache);
			
			final String key = "greeting";
			
			final Element putGreeting = new Element(key, "Hello, World!");
			
			cache.put(putGreeting);
			
			final Element getGreeting = cache.get(key);
			
			System.out.println(getGreeting.getObjectValue());
		} finally {
			cacheManager.shutdown();
		}
	}
}
