package br.com.project.TRFamilia.dto;

public class AuthInfoDto {
	String email;
	String role;
	Integer userId;

	public AuthInfoDto(String email, String role, Integer userId) {
		this.email = email;
		this.role = role;
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public Integer getUserId() {
		return userId;
	}
}
