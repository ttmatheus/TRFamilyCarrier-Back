package br.com.project.TRFamilia.dto;

public class UserInfoDTO {
	private final String userRole;

	private final Long userId;

	private final String userEmail;

	public UserInfoDTO(String role, Long userId, String userEmail) {
		this.userRole = role;
		this.userId = userId;
		this.userEmail = userEmail;
	}

	public String getRole() {
		return userRole;
	}
	public Long getUserId() {
		return userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
}
