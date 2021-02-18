package com.curso.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.models.security.UsuarioDetails;
import com.curso.security.JWTUtil;
import com.curso.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping(value = "/refresh-token")
	public ResponseEntity<?> refreshToken(HttpServletResponse response) {
		UsuarioDetails user = (UsuarioDetails) service.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
}
