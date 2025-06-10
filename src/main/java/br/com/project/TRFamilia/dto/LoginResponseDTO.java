package br.com.project.TRFamilia.dto;

public class LoginResponseDTO {
	private final String token;
	private final UserInfoDTO user;

	public LoginResponseDTO(String token, UserInfoDTO user) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public UserInfoDTO getUser() {
		return user;
	}
}
