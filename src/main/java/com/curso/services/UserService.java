package com.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.exceptions.CustomException;
import com.curso.models.Usuario;
import com.curso.models.security.UsuarioDetails;
import com.curso.repositories.UsuarioRepository;

@Service
public class UserService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioDetails authenticated() {
		try {
			return (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	public Usuario create(Usuario usuario) {
		try {
			if (usuario.getId() != null && repository.existsById(usuario.getId()))
				throw new CustomException("ID de Usuário (" + usuario.getId() + ") já utilizado.",
						HttpStatus.BAD_REQUEST);
			if (usuario.getUsername() != null && repository.findByUsername(usuario.getUsername()).isPresent())
				throw new CustomException("Nome de usuário já registrado", HttpStatus.BAD_REQUEST);
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			return repository.save(usuario);
		} catch (Exception e) {
			throw new CustomException(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
