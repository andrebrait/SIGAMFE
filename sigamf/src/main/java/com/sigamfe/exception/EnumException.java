package com.sigamfe.exception;

public class EnumException extends GenericException {

	private static final long serialVersionUID = -8741930908259994622L;

	public EnumException(String dbData) {
		super(new IllegalArgumentException(dbData + "não é um valor conhecido."));
	}

}
