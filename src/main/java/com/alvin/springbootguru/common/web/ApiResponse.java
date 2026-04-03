package com.alvin.springbootguru.common.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
	private boolean success;
	private int code;
	private String message;
	private T data;
	private Object meta;

	public static <T> ApiResponse<T> ok(String message, T data, Object meta) {
		return new ApiResponse<>(true, 200, message, data, meta);
	}

	public static <T> ApiResponse<T> ok(String message, T data) {
		return ok(message, data, null);
	}

	public static ApiResponse<Void> ok(String message) {
		return ok(message, null, null);
	}

	public static <T> ApiResponse<T> created(String message, T data) {
		return new ApiResponse<>(true, 201, message, data, null);
	}

	public static <T> ApiResponse<T> fail(int code, String message, Object meta) {
		return new ApiResponse<>(false, code, message, null, meta);
	}
}
