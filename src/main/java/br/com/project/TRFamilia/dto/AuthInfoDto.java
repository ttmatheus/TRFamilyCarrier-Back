package br.com.project.TRFamilia.dto;

import lombok.Getter;

@Getter
public class AuthInfoDto {
	String email;
	String role;
	String name;
	Integer userId;

	public AuthInfoDto(String email, String role, Integer userId, String name) {
		this.email = email;
		this.role = role;
		this.userId = userId;
		this.name = name;
	}
}
