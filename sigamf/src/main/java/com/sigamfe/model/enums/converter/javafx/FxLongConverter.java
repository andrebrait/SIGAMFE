package com.sigamfe.model.enums.converter.javafx;

import org.apache.commons.lang3.StringUtils;

import javafx.util.StringConverter;

public class FxLongConverter extends StringConverter<Long> {

	@Override
	public String toString(Long object) {
		if (object == null) {
			return null;
		}
		return object.toString();
	}

	@Override
	public Long fromString(String string) {
		if (StringUtils.isBlank(string)) {
			return null;
		}
		try {
			return Long.parseLong(string);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
