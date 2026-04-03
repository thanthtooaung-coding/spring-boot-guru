package com.alvin.springbootguru.common.service;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import com.alvin.springbootguru.common.cache.CacheRegionProvider;

/**
 * CRUD service with Spring Cache on all read/write paths. Subclasses must implement
 * {@link #cacheRegion()} and register that name in {@code spring.cache.cache-names}.
 */
@CacheConfig(cacheResolver = "entityCacheResolver")
@Transactional
public abstract class CachingBaseServiceImpl<E, D, C, U> extends BaseServiceImpl<E, D, C, U>
		implements CacheRegionProvider {

	public abstract String cacheRegion();

	@Override
	@Cacheable(key = "#id", sync = true)
	@Transactional(readOnly = true)
	public D getById(Long id) {
		return super.getById(id);
	}

	@Override
	@Cacheable(key = "'all'", sync = true)
	@Transactional(readOnly = true)
	public List<D> getAll() {
		return super.getAll();
	}

	@Override
	@Caching(put = @CachePut(key = "#result.id"), evict = @CacheEvict(key = "'all'"))
	public D create(C request) {
		return super.create(request);
	}

	@Override
	@Caching(put = @CachePut(key = "#id"), evict = @CacheEvict(key = "'all'"))
	public D update(Long id, U request) {
		return super.update(id, request);
	}

	@Override
	@Caching(evict = { @CacheEvict(key = "#id"), @CacheEvict(key = "'all'") })
	public void delete(Long id) {
		super.delete(id);
	}
}
