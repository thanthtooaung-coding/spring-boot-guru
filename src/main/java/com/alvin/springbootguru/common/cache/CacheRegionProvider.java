package com.alvin.springbootguru.common.cache;

/**
 * Implemented by services that use {@link com.alvin.springbootguru.common.service.CachingBaseServiceImpl}
 * so {@link EntityCacheResolver} can bind cache operations to the correct region.
 */
public interface CacheRegionProvider {

	String cacheRegion();
}
