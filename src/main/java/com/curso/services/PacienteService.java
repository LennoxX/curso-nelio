package com.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.exceptions.CustomException;
import com.curso.models.Paciente;
import com.curso.repositories.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;

	public List<Paciente> findAll() {
		return repository.findAll();
	}

	public Paciente findById(Long id) {
		Optional<Paciente> opt = repository.findById(id);
		return opt.orElseThrow(() -> new CustomException("Paciente não encontrado", HttpStatus.NOT_FOUND));
	}

	@Transactional
	public Paciente create(Paciente paciente) {
		try {
			return repository.save(paciente);
		} catch (Exception e) {
			throw new CustomException(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Transactional
	public Paciente update(Paciente paciente) {
		try {
			return repository.save(paciente);
		} catch (Exception e) {
			throw new CustomException(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.delete(repository.getOne(id));
		} catch (Exception e) {
			throw new CustomException(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
