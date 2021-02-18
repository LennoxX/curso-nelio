package com.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.curso.exceptions.CustomException;
import com.curso.models.Usuario;
import com.curso.models.security.UsuarioDetails;
import com.curso.repositories.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		try {
			Optional<Usuario> opt = repository.findByUsername(username);

			Usuario usuario = opt.get();
			opt.orElseThrow();
			UsuarioDetails userDetails = new UsuarioDetails(usuario.getUsername(), usuario.getPassword(),
					usuario.getAtivo(), false, false, true, usuario.getNivel().name());
			return userDetails;
		} catch (Exception e) {
			throw new CustomException("Usuário ou senha inválidos.", HttpStatus.UNAUTHORIZED);
		}

	}

}
