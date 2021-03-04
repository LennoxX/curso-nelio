package com.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.exceptions.CustomException;
import com.curso.models.Profissional;
import com.curso.repositories.ProfissionalRepository;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Profissional> findAll() {
		return repository.findAll();
	}

	public Profissional findById(Long id) {
		Optional<Profissional> opt = repository.findById(id);
		return opt.orElseThrow(() -> new CustomException("Paciente não encontrado", HttpStatus.NOT_FOUND));
	}
	
	public Profissional findByUserId(Long userId) {
		Optional<Profissional> opt = repository.findById(userId);
		return opt.orElseThrow(() -> new CustomException("Paciente não encontrado", HttpStatus.NOT_FOUND));
	}

	@Transactional
	public Profissional create(Profissional profissional) {
		try {
			profissional.getUsuario().setPassword(passwordEncoder.encode(profissional.getUsuario().getPassword()));
			return repository.save(profissional);
		} catch (Exception e) {
			throw new CustomException(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Transactional
	public Profissional update(Profissional paciente) {
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
