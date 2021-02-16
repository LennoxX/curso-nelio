package com.curso.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.models.Categoria;
import com.curso.services.CategoriaService;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@GetMapping
	public String teste() {
		return "Teste OK!";
	}
	
	@GetMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
}
