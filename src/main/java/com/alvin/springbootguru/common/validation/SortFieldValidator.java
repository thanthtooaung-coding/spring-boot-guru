package com.alvin.springbootguru.common.validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SortFieldValidator {

	private SortFieldValidator() {
	}

	/**
	 * Ensures {@code sortBy} matches a field on {@code dtoOrEntityClass} (including superclasses).
	 */
	public static void validateSortBy(String sortBy, Class<?> dtoOrEntityClass) {
		if (sortBy == null || sortBy.isBlank()) {
			return;
		}
		List<String> valid = new ArrayList<>();
		Class<?> current = dtoOrEntityClass;
		while (current != null && current != Object.class) {
			valid.addAll(Arrays.stream(current.getDeclaredFields()).map(Field::getName).toList());
			current = current.getSuperclass();
		}
		if (!valid.contains(sortBy)) {
			throw new IllegalArgumentException(
					"Invalid sort field '" + sortBy + "'. Allowed: " + String.join(", ", valid));
		}
	}
}
