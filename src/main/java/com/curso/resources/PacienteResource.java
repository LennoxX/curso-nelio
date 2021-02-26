package com.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<Paciente> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(paciente));
	}

	@PutMapping
	public ResponseEntity<Paciente> update(@RequestBody Paciente paciente) {
		System.out.println(paciente);
		return ResponseEntity.ok().body(service.update(paciente));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
