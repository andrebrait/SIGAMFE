package com.sigamfe.util;

import org.apache.commons.lang3.StringUtils;

import javafx.util.StringConverter;

public class TelefoneUtils {

	public static String getTelefoneAsString(Long telefone) {
		return MaskValidator.getVersionByLength(String.valueOf(telefone), null, MaskValidator.TELEFONE_8_VALIDATOR,
				MaskValidator.TELEFONE_9_VALIDATOR);
	}

	public static Long getTelefoneAsLong(String telefone) {
		if (telefone == null) {
			return null;
		}
		return Long.parseLong(StringUtils
				.remove(StringUtils.remove(StringUtils.remove(StringUtils.deleteWhitespace(telefone), "("), ")"), "-"));
	}

	public static class TelefoneConverter extends StringConverter<Long> {

		@Override
		public String toString(Long object) {
			return getTelefoneAsString(object);
		}

		@Override
		public Long fromString(String string) {
			return getTelefoneAsLong(string);
		}

	}

}
