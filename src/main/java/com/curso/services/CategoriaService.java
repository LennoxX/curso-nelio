package com.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.curso.exceptions.CustomException;
import com.curso.models.Categoria;
import com.curso.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Categoria findById(Long id) {
		Optional<Categoria> opt = repository.findById(id);
		return opt.orElseThrow(() -> new CustomException("Categoria não encontrada", HttpStatus.NOT_FOUND));
	}
}
