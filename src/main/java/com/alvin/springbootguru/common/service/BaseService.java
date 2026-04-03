package com.alvin.springbootguru.common.service;

import java.util.List;

public interface BaseService<D, C, U> {
	D getById(Long id);

	List<D> getAll();

	D create(C request);

	D update(Long id, U request);

	void delete(Long id);
}
