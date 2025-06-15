package br.com.project.TRFamilia.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
	private final int code;
	private final String error;
	private final HttpStatus status;

	public ApiException(int code, String error, HttpStatus status) {
		super(error);
		this.code = code;
		this.error = error;
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public String getError() {
		return error;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
