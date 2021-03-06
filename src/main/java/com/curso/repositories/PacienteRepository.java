package com.curso.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.models.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	Optional<Paciente> findByUsuarioId(Long usuarioId);

}
