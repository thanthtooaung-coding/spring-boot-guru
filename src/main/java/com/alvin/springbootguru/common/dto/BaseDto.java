package com.alvin.springbootguru.common.dto;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDto {
	private Long id;
	private Instant createdAt;
	private Instant updatedAt;
}
