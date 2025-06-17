package br.com.project.TRFamilia.dto;

import br.com.project.TRFamilia.models.Driver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDriverDTO {
	private Long id;
	private String name;

	public ResponseDriverDTO(Driver driver) {
		this.id = driver.getId();
		this.name = driver.getUser().getName();
	}
}
