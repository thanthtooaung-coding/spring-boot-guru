package com.alvin.springbootguru.common.util;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ObjectMapUtils {

	private ObjectMapUtils() {
	}

	/**
	 * Non-null declared field values keyed by field name (for GenericSpecification field list).
	 */
	public static Map<String, Object> toMap(Object obj) {
		if (obj == null) {
			return Map.of();
		}
		Map<String, Object> result = new LinkedHashMap<>();
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				Object value = field.get(obj);
				if (value != null) {
					result.put(field.getName(), value);
				}
			} catch (IllegalAccessException ignored) {
				// skip
			}
		}
		return result;
	}

	/**
	 * Like {@link #toMap(Object)} but renames keys using {@code filterFieldName -> entityAttributeName}.
	 */
	public static Map<String, Object> toMap(Object obj, Map<String, String> filterFieldToEntityField) {
		if (obj == null) {
			return Map.of();
		}
		Map<String, Object> result = new LinkedHashMap<>();
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				Object value = field.get(obj);
				if (value != null) {
					String fieldName = field.getName();
					String mapped = filterFieldToEntityField != null && filterFieldToEntityField.containsKey(fieldName)
							? filterFieldToEntityField.get(fieldName)
							: fieldName;
					result.put(mapped, value);
				}
			} catch (IllegalAccessException ignored) {
				// skip
			}
		}
		return result;
	}
}
