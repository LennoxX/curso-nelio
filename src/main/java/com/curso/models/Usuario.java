package com.curso.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.curso.enums.EnumTipoUsuario;
import com.sun.istack.NotNull;

@Entity
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "usuario_sequence", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "usuario_sequence", sequenceName = "usuario_sequence")
	private Long id;

	@NotEmpty(message = "*Campo 'Username', obrigatório.")
	@NotBlank
	@NotNull
	private String username;

	@NotEmpty(message = "*Campo 'Password', obrigatório.")
	@NotBlank
	@NotNull
	private String password;

	@Enumerated(EnumType.STRING)
	@NotNull
	private EnumTipoUsuario nivel;

	private boolean ativo;

	@Column(columnDefinition = "text")
	private String imagemPerfil;

	public Usuario() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EnumTipoUsuario getNivel() {
		return nivel;
	}

	public void setNivel(EnumTipoUsuario nivel) {
		this.nivel = nivel;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getImagemPerfil() {
		return imagemPerfil;
	}

	public void setImagemPerfil(String imagemPerfil) {
		this.imagemPerfil = imagemPerfil;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", nivel=" + nivel
				+ ", ativo=" + ativo + "]";
	}

}
