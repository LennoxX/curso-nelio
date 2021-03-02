package com.curso.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.models.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
	
	Optional<Profissional> findByUsuarioId(Long usuarioId);

}
