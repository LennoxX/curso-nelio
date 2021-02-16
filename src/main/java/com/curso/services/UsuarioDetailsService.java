package com.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> opt = repository.findByUsername(username);
		opt.orElseThrow(() -> new CustomException("Usuário ou senha inválidos.", HttpStatus.UNAUTHORIZED));
		Usuario usuario = opt.get();
		UsuarioDetails userDetails = new UsuarioDetails(usuario.getUsername(), usuario.getPassword(),
				usuario.getAtivo(), false, false, true, usuario.getNivel().name());
		return userDetails;
	}

}
