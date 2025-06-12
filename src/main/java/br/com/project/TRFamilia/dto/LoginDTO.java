package br.com.project.TRFamilia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginDTO {
	
	@NotBlank(message = "Email cannot be empty.")
	@Email(message = "Invalid email.")
	private String email;

	@NotBlank(message = "Password cannot be empty.")
	@Size(min = 8, message = "Password must contain at least 8 characters.")
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&#]{8,}$",
		message = "The password must contain at least one uppercase letter, one lowercase letter, one number, and one special character."
	)
	private String password;

	public LoginDTO() {}

	public LoginDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
