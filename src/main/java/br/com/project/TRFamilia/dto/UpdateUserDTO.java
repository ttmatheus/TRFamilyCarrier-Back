package br.com.project.TRFamilia.dto;

import br.com.project.TRFamilia.enums.UserType;
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

	private UserType userType;
}
