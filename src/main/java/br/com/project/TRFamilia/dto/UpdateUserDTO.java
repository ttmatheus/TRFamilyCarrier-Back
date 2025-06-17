package br.com.project.TRFamilia.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateUserDTO {

	private String name;

	@Email(message = "Invalid email")
	private String email;

	private boolean active;
}
