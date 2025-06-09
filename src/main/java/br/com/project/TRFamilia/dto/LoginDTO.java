package br.com.project.TRFamilia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginDTO {
	
	@NotBlank(message = "Email não pode estar vazio.")
	@Email(message = "Email inválido.")
	private String email;

	@NotBlank(message = "A senha não pode estar vazia.")
	@Size(min = 8, message = "A senha deve conter pelo menos 8 caracteres.")
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&#]{8,}$",
		message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial."
	)
	private String senha;

	public LoginDTO() {}

	public LoginDTO(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
