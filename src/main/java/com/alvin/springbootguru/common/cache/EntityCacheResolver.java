package com.alvin.springbootguru.common.cache;

import java.util.Collection;
import java.util.Collections;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.stereotype.Component;

@Component
public class EntityCacheResolver implements CacheResolver {

	private final CacheManager cacheManager;

	public EntityCacheResolver(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
		Object target = context.getTarget();
		if (!(target instanceof CacheRegionProvider provider)) {
			throw new IllegalStateException(
					"Cached service must implement CacheRegionProvider: " + target.getClass().getName());
		}
		Cache cache = cacheManager.getCache(provider.cacheRegion());
		if (cache == null) {
			throw new IllegalStateException("No cache registered for region: " + provider.cacheRegion());
		}
		return Collections.singletonList(cache);
	}
}
