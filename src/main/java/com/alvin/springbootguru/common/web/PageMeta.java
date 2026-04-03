package com.alvin.springbootguru.common.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageMeta {
	private int page;
	private int size;
	private long totalElements;
	private int totalPages;
}
