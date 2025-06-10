package br.com.project.TRFamilia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {

	@NotBlank(message = "O nome não pode estar vazio.")
	private String nome;

	@NotBlank(message = "O email não pode estar vazio.")
	@Email(message = "Email inválido.")
	private String email;

	// Validando que a senha não deve ser nula ou vazia
	@NotBlank(message = "A senha não pode estar vazia.")
	// Validando o tamanho mínimo da senha
	@Size(min = 8, message = "A senha deve conter pelo menos 8 caracteres.")
	// Validando a senha
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&#]{8,}$",
		message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial."
	)
	private String senha;

	public CreateUserDTO() {}

	public CreateUserDTO(String nome, String email, String senhaHash) {
		this.nome = nome;
		this.email = email;
		this.senha = senhaHash;
	}

	public String getPassword() {
		return senha;
	}

	public void setPassword(String password) {
		this.senha = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}
}
