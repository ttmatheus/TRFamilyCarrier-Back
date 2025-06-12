package br.com.project.TRFamilia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {

	@NotBlank(message = "Name cannot be empty.")
	private String name;

	@NotBlank(message = "Email cannot be empty.")
	@Email(message = "Invalid email")
	private String email;

	// Validando que a senha não deve ser nula ou vazia
	@NotBlank(message = "Password cannot be empty.")
	// Validando o tamanho mínimo da senha
	@Size(min = 8, message = "Password must contain at least 8 characters.")
	// Validando a senha
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&#]{8,}$",
		message = "The password must contain at least one uppercase letter, one lowercase letter, one number, and one special character."
	)
	private String password;

	public CreateUserDTO() {}

	public CreateUserDTO(String nome, String email, String password) {
		this.name = nome;
		this.email = email;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String name) {
		this.name = name;
	}
}
