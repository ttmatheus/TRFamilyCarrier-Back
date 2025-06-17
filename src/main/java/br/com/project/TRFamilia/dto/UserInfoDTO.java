package br.com.project.TRFamilia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {
	private final String userType;

	private final Long userId;

	private final String userEmail;

	private final String userName;

	public UserInfoDTO(String userType, Long userId, String userEmail, String userName) {
		this.userType = userType;
		this.userId = userId;
		this.userEmail = userEmail;
		this.userName = userName;
	}
}

