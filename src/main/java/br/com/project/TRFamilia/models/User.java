package br.com.project.TRFamilia.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String email;

	@Column(name = "senha_hash")
	private String senhaHash;

	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	private boolean ativo;

	@CreationTimestamp
	@Column(name = "criado_em", updatable = false)
	private LocalDateTime criadoEm;

	public User() {}

	public User(String nome, String email, String senhaHash, TipoUsuario tipo, boolean ativo) {
		this.nome = nome;
		this.email = email;
		this.senhaHash = senhaHash;
		this.tipo = tipo;
		this.ativo = ativo;
	}

	public String getHashPassword() { return senhaHash; }

	public void setHashPassword(String password) { this.senhaHash = password; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public Long getId() { return id; }

	public String getNome() { return nome; }

	public void setNome(String name) { this.nome = name; }

	public TipoUsuario getTipo() { return tipo; }

	public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }

	public boolean isAtivo() { return ativo; }

	public void setAtivo(boolean ativo) { this.ativo = ativo; }

	public LocalDateTime getCriadoEm() { return criadoEm; }
}
