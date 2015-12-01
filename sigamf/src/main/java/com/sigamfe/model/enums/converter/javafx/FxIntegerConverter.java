package com.sigamfe.model.enums.converter.javafx;

import org.apache.commons.lang3.StringUtils;

import javafx.util.StringConverter;

public class FxIntegerConverter extends StringConverter<Integer> {

	@Override
	public String toString(Integer object) {
		if (object == null) {
			return null;
		}
		return object.toString();
	}

	@Override
	public Integer fromString(String string) {
		if (StringUtils.isBlank(string)) {
			return null;
		}
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
