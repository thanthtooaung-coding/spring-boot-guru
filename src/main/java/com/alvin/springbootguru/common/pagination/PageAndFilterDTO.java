package com.alvin.springbootguru.common.pagination;

import java.io.Serial;
import java.io.Serializable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageAndFilterDTO<T> implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Builder.Default
	@Min(1)
	private int pageNumber = 1;

	@Builder.Default
	@Min(1)
	@Max(500)
	private int pageSize = 10;

	private String sortBy;

	private SortOrder sortOrder;

	private T filter;

	@Hidden
	@JsonIgnore
	public PageRequest getPageRequest() {
		if (sortBy != null && !sortBy.isBlank() && !"string".equalsIgnoreCase(sortBy)) {
			Sort.Direction direction = sortOrder == SortOrder.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
			return PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
		}
		return PageRequest.of(pageNumber - 1, pageSize);
	}
}
