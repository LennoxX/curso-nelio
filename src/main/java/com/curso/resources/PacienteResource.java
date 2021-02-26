package com.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.models.Paciente;
import com.curso.services.PacienteService;

@RestController
@RequestMapping(value = "pacientes")
@PreAuthorize("hasAuthority('PROFISSIONAL')")
public class PacienteResource {

	@Autowired
	private PacienteService service;

	@GetMapping
	public ResponseEntity<List<Paciente>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("{id}")
	public Paciente findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Paciente create(@RequestBody Paciente paciente) {
		return service.create(paciente);
	}

}
