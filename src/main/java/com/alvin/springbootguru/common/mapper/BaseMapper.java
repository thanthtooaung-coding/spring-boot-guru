package com.alvin.springbootguru.common.mapper;

public interface BaseMapper<E, D> {
	D toDto(E entity);
}
