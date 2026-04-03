package com.alvin.springbootguru.common.specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * Dynamic AND filters: string values use case-insensitive LIKE; other types use equality.
 * Dot paths use a single LEFT join on the first segment (same idea as jobonic-backend).
 */
public class GenericSpecification<T> {

	public static <E> Specification<E> hasKeyword(String keyword, Set<String> columns) {
		return (root, query, criteriaBuilder) -> {
			if (keyword == null || keyword.isEmpty()) {
				return criteriaBuilder.conjunction();
			}
			String lowerKeyword = "%" + keyword.toLowerCase() + "%";
			Predicate[] predicates = columns.stream()
					.map(column -> criteriaBuilder.like(criteriaBuilder.lower(root.get(column)), lowerKeyword))
					.toArray(Predicate[]::new);
			return criteriaBuilder.or(predicates);
		};
	}

	public static <E> Specification<E> hasServiceKeywords(String[] keywords, Set<String> fields) {
		return (root, query, criteriaBuilder) -> {
			if (keywords == null || keywords.length == 0) {
				return criteriaBuilder.conjunction();
			}
			List<Predicate> keywordPredicates = new ArrayList<>();
			for (String keyword : keywords) {
				List<Predicate> fieldPredicates = new ArrayList<>();
				for (String field : fields) {
					fieldPredicates.add(criteriaBuilder.like(
							criteriaBuilder.lower(root.get(field)), "%" + keyword.toLowerCase() + "%"));
				}
				keywordPredicates.add(criteriaBuilder.or(fieldPredicates.toArray(Predicate[]::new)));
			}
			return criteriaBuilder.or(keywordPredicates.toArray(Predicate[]::new));
		};
	}

	public static <E> Specification<E> hasKeyword(UUID searchKeywordUuid, Set<String> fields) {
		return (root, query, criteriaBuilder) -> {
			if (searchKeywordUuid == null) {
				return criteriaBuilder.conjunction();
			}
			List<Predicate> keywordPredicates = new ArrayList<>();
			for (String field : fields) {
				keywordPredicates.add(criteriaBuilder.equal(root.get(field), searchKeywordUuid));
			}
			return criteriaBuilder.or(keywordPredicates.toArray(Predicate[]::new));
		};
	}

	public Specification<T> getSpecification(Map<String, Object> keywordMap, List<String> fields) {
		return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			if (keywordMap == null || keywordMap.isEmpty() || fields == null || fields.isEmpty()) {
				return cb.conjunction();
			}

			List<Predicate> predicates = new ArrayList<>();
			Map<String, Join<?, ?>> joins = new HashMap<>();

			for (String field : fields) {
				Object value = keywordMap.get(field);
				if (value == null) {
					continue;
				}

				String[] parts = field.split("\\.");
				Path<?> path;
				if (parts.length == 1) {
					path = root.get(parts[0]);
				} else {
					Join<?, ?> join = joins.computeIfAbsent(parts[0], j -> root.join(j, JoinType.LEFT));
					path = join.get(parts[1]);
				}

				if (value instanceof String stringValue) {
					if (stringValue.trim().isEmpty()) {
						continue;
					}
					String likePattern = "%" + stringValue.toLowerCase() + "%";
					predicates.add(cb.like(cb.lower(path.as(String.class)), likePattern));
				} else {
					predicates.add(cb.equal(path, value));
				}
			}

			if (predicates.isEmpty()) {
				return cb.conjunction();
			}
			return cb.and(predicates.toArray(Predicate[]::new));
		};
	}
}
