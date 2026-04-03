package com.alvin.springbootguru.common.helper;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alvin.springbootguru.common.pagination.PaginationDTO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PaginationHelper {

	public static <S, T> PaginationDTO<S> toPagination(Page<T> page, List<S> content) {
		return new PaginationDTO<>(
				page.getNumber() + 1,
				page.getSize(),
				page.getTotalPages(),
				page.getTotalElements(),
				content,
				page.isLast());
	}
}
