package com.liuyf.demos.cache.ehcache3;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.CacheManagerBuilder;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.CacheConfigurationBuilder;

public class Ehcache3KickOff {

	public static void main(String[] args) {
		
		CacheConfiguration<Long,String> cacheConfigurationBuilder = CacheConfigurationBuilder.newCacheConfigurationBuilder().buildConfig(Long.class, String.class);
		
		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.withCache("preConfigured", cacheConfigurationBuilder).build(true);
		
		@SuppressWarnings("unused")
		Cache<Long, String> preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);
		
		Cache<Long, String> myCache = cacheManager.createCache("myCache", cacheConfigurationBuilder);
		
		myCache.put(1L, "da one!");
		
		String value = myCache.get(1L);
		System.out.println(value);
		
		cacheManager.close();
	}
}
