package com.curso.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.enums.EnumTipoUsuario;
import com.curso.models.Paciente;
import com.curso.models.Pessoa;
import com.curso.models.Profissional;
import com.curso.models.Usuario;
import com.curso.security.JWTUtil;
import com.curso.services.PacienteService;
import com.curso.services.ProfissionalService;
import com.curso.services.UserService;

@RestController
@RequestMapping(value = "profile")
public class ProfileResource {
	
	@Autowired
	private UserService service;
	

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private JWTUtil jwtUtil;

	@GetMapping("/user")
	public ResponseEntity<Pessoa> getUsuarioFromToken(@RequestHeader(name = "Authorization") String token) {
		try {
			Usuario usuario = service.findByUsername(jwtUtil.getUsername(token.split("Bearer ")[1]));
			if (usuario != null) {
				if (usuario.getNivel() == EnumTipoUsuario.PACIENTE) {
					Paciente p = pacienteService.findByUserId(usuario.getId());
					usuario.setPassword("");
					usuario.setId(null);
					return ResponseEntity.ok().body(p);
				} else {
					Profissional p = profissionalService.findByUserId(usuario.getId());
					usuario.setPassword("");
					usuario.setId(null);
					return ResponseEntity.ok().body(p);
				}
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(401).build();
		}
	}

	

	@PutMapping("/user/update")
	public ResponseEntity<Pessoa> updateDados(@RequestHeader(name = "Authorization") String token, @RequestBody Pessoa p) {
		try {
			Usuario usuario = service.findByUsername(jwtUtil.getUsername(token.split("Bearer ")[1]));
			if (usuario != null) {
				if (usuario.getNivel() == EnumTipoUsuario.PACIENTE) {
					Paciente tmp = pacienteService.findByUserId(usuario.getId());
					tmp.setDataNascimento(p.getDataNascimento());
					tmp.setEmail(p.getEmail());
					tmp.setNome(p.getNome());
					tmp.setSexo(p.getSexo());
					tmp.setTelefone(p.getTelefone());
					
					if(p.getUsuario().getImagemPerfil() != null && p.getUsuario().getImagemPerfil() != tmp.getUsuario().getImagemPerfil()) {
						tmp.getUsuario().setImagemPerfil(p.getUsuario().getImagemPerfil());
					}
					
					pacienteService.update(tmp);
					
					return ResponseEntity.noContent().build();
				} else {
					Profissional tmp = profissionalService.findByUserId(usuario.getId());
					tmp.setDataNascimento(p.getDataNascimento());
					tmp.setEmail(p.getEmail());
					tmp.setNome(p.getNome());
					tmp.setSexo(p.getSexo());
					tmp.setTelefone(p.getTelefone());
					
					if(p.getUsuario().getImagemPerfil() != null && p.getUsuario().getImagemPerfil() != tmp.getUsuario().getImagemPerfil()) {
						tmp.getUsuario().setImagemPerfil(p.getUsuario().getImagemPerfil());
					}
					
					profissionalService.update(tmp);
					
					return ResponseEntity.noContent().build();
				}
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
	}
	
	

}
