package com.curso.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.curso.enums.EnumSexo;

@Entity
public class Paciente extends Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "paciente_sequence", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence")
	private Long id;

	@NotEmpty(message = "*Campo 'Nome', obrigatório.")
	@NotBlank
	@NotNull
	protected String nome;

	@Enumerated(EnumType.STRING)
	@NotNull
	protected EnumSexo sexo;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date dataNascimento;

	@NotEmpty(message = "*Campo 'Email', obrigatório.")
	@NotBlank
	@NotNull
	@Column(unique = true)
	protected String email;

	@NotEmpty(message = "*Campo 'Telefone', obrigatório.")
	@NotBlank
	@NotNull
	@Column(unique = true)
	protected String telefone;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	protected Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnumSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
