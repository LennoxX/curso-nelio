package com.curso;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.curso.enums.EnumTipoUsuario;
import com.curso.models.Usuario;
import com.curso.repositories.UsuarioRepository;

@SpringBootApplication
public class ProjetoCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoCursoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			iniciarUsuario(usuarioRepository, passwordEncoder);
		};
	}

	public void iniciarUsuario(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
		List<Usuario> usuarios = repository.findAll();

		if (usuarios.isEmpty()) {
			
			Usuario usuario = new Usuario();
			usuario.setUsername("user"); 
			usuario.setPassword(passwordEncoder.encode("user"));
			usuario.setAtivo(true);
			usuario.setNivel(EnumTipoUsuario.USUARIO);

			repository.save(usuario);
		}
	}

}
