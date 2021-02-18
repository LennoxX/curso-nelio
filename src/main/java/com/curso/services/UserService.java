package com.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.curso.models.security.UsuarioDetails;

@Service
public class UserService {
	
	@Autowired
	private UsuarioDetailsService service;

	public UsuarioDetails authenticated() {
		try {
			return (UsuarioDetails) service.loadUserByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		} catch (Exception e) {
			
			return null;
		}

	}

}
