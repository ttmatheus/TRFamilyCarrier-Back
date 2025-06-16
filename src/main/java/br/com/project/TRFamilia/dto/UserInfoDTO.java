package br.com.project.TRFamilia.dto;

public class UserInfoDTO {
	private final String userRole;

	private final Long userId;

	private final String userEmail;

	private final String userName;

	public UserInfoDTO(String role, Long userId, String userEmail, String userName) {
		this.userRole = role;
		this.userId = userId;
		this.userEmail = userEmail;
		this.userName = userName;
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
	public String getUserName() {
		return userName;
	}
}

