package com.alvin.springbootguru.common.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * Imperative cache utilities for code paths that bypass annotated service methods.
 */
@Component
public class EntityCacheOperations {

	private final CacheManager cacheManager;

	public EntityCacheOperations(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void evict(String region, Object key) {
		Cache cache = cacheManager.getCache(region);
		if (cache != null) {
			cache.evict(key);
		}
	}

	public void evictAll(String region) {
		Cache cache = cacheManager.getCache(region);
		if (cache != null) {
			cache.clear();
		}
	}

	public void evictRegion(CacheRegionProvider provider, Object key) {
		evict(provider.cacheRegion(), key);
	}

	public void clearRegion(CacheRegionProvider provider) {
		evictAll(provider.cacheRegion());
	}
}
