package br.com.project.TRFamilia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateUserDTO {

	@NotBlank
	private String name;

	@NotBlank
	@Email(message = "Invalid email")
	private String email;

	// Validando o tamanho mínimo da senha
	@Size(min = 8, message = "Password must contain at least 8 characters.")
	// Validando a senha
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&#]{8,}$",
		message = "The password must contain at least one uppercase letter, one lowercase letter, one number, and one special character."
	)
	private String password;

	private boolean active;

	public CreateUserDTO() {}

	public CreateUserDTO(String name, String email, String password, boolean active) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.active = active;
	}
}
