package com.sigamfe.exception;

/**
 * The Class SIGAMFException.
 */
public class SIGAMFException extends RuntimeException {

	private static final long serialVersionUID = 1096840692478003020L;

	public SIGAMFException() {
		super();
	}

	public SIGAMFException(String message) {
		super(message);
	}

	public SIGAMFException(String message, Throwable cause) {
		super(message, cause);
	}

	public SIGAMFException(Throwable cause) {
		super(cause);
	}

	protected SIGAMFException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
