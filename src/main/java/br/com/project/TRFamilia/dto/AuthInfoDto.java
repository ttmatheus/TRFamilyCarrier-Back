package br.com.project.TRFamilia.dto;

import lombok.Getter;

@Getter
public class AuthInfoDto {
	String email;
	String userType;
	String name;
	Integer userId;

	public AuthInfoDto(String email, String userType, Integer userId, String name) {
		this.email = email;
		this.userType = userType;
		this.userId = userId;
		this.name = name;
	}
}
