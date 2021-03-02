package com.curso;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.curso.enums.EnumSexo;
import com.curso.enums.EnumTipoUsuario;
import com.curso.models.Profissional;
import com.curso.models.Usuario;
import com.curso.repositories.ProfissionalRepository;

@SpringBootApplication
public class ProjetoCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoCursoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ProfissionalRepository repository, PasswordEncoder passwordEncoder) {
		return args -> {
			iniciarUsuario(repository, passwordEncoder);
		};
	}

	public void iniciarUsuario(ProfissionalRepository repository, PasswordEncoder passwordEncoder) {
		List<Profissional> usuarios = repository.findAll();

		if (usuarios.isEmpty()) {

			Profissional p = new Profissional();
			p.setNome("Lucas");
			p.setDataNascimento(new Date());
			p.setSexo(EnumSexo.MASCULINO);
			p.setTelefone("98981904611");
			p.setEmail("lucasraphael.fernandes@hotmail.com");
			Usuario usuario = new Usuario();
			usuario.setUsername("profissional");
			usuario.setPassword(passwordEncoder.encode("profissional"));
			usuario.setAtivo(true);
			usuario.setNivel(EnumTipoUsuario.PROFISSIONAL);
			p.setUsuario(usuario);

			repository.save(p);
		}
	}

}
