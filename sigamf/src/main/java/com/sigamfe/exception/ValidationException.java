package com.sigamfe.exception;

import lombok.Getter;

public class ValidationException extends GenericException {

	private static final long serialVersionUID = 7820384636394878545L;

	@Getter
	private String campo;

	public ValidationException() {
		super();
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(String campo, String message) {
		super(message);
		this.campo = campo;
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	protected ValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
