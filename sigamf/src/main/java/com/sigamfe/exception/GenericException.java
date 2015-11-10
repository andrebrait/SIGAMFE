package com.sigamfe.exception;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class SIGAMFException.
 */
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1096840692478003020L;

	@Getter
	@Setter
	private List<GenericException> excecoesFilhas;

	public GenericException() {
		super();
	}

	public GenericException(String message) {
		super(message);
	}

	public GenericException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericException(Throwable cause) {
		super(cause);
	}

	protected GenericException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
