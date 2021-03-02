package com.curso.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.models.Usuario;
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
	
	@GetMapping("/user")
	public ResponseEntity<Usuario> getUsuarioFromToken(@RequestHeader(name = "Authorization") String token) {
		try {
			Usuario usuario = service.findByUsername(jwtUtil.getUsername(token.split("Bearer ")[1]));
			if (usuario != null) {
				usuario.setPassword("");
				usuario.setId(null);
				return ResponseEntity.ok().body(usuario);
			} else {
				return ResponseEntity.notFound().build();
			}
		}catch (Exception e) {
			return ResponseEntity.status(401).build();
		}
	
	}

	@GetMapping(value = "/validate")
	public ResponseEntity<?> validate(HttpServletResponse response) {
		return ResponseEntity.noContent().build();
	}

}
