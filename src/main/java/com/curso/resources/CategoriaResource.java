package com.curso.resources;

import java.util.List;

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
@PreAuthorize("hasAuthority('ADMIN')")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@GetMapping
	
	public ResponseEntity<List<Categoria>> teste() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("{id}")

	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
}
