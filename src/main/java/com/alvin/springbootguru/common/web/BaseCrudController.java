package com.alvin.springbootguru.common.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alvin.springbootguru.common.service.BaseService;

@Validated
public abstract class BaseCrudController<D, C, U> {

	protected abstract BaseService<D, C, U> service();

	@GetMapping
	public ApiResponse<List<D>> getAll() {
		List<D> items = service().getAll();
		PageMeta meta = new PageMeta(0, items.size(), items.size(), items.isEmpty() ? 0 : 1);
		return ApiResponse.ok("OK", items, meta);
	}

	@GetMapping("/{id}")
	public ApiResponse<D> getById(@PathVariable Long id) {
		return ApiResponse.ok("OK", service().getById(id));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<D>> create(@RequestBody @Validated C request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.created("Created", service().create(request)));
	}

	@PutMapping("/{id}")
	public ApiResponse<D> update(@PathVariable Long id, @RequestBody @Validated U request) {
		return ApiResponse.ok("Updated", service().update(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
		service().delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok("Deleted"));
	}
}
