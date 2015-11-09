package com.sigamfe.util;

import org.apache.commons.lang3.StringUtils;

public class TelefoneUtils {

	public static String getTelefoneAsString(Long telefone) {
		if (telefone == null) {
			return null;
		}
		char[] numTel = Long.toString(telefone).toCharArray();
		int prefixoLen = numTel.length == 12 ? 4 : 5;
		return "(" + String.valueOf(numTel, 0, 1) + ")" + String.valueOf(numTel, 2, 4) + "-"
				+ String.valueOf(numTel, 2 + prefixoLen, numTel.length - (2 + prefixoLen));
	}

	public static Long getTelefoneAsLong(String telefone) {
		if (telefone == null) {
			return null;
		}
		return Long.parseLong(StringUtils
				.remove(StringUtils.remove(StringUtils.remove(StringUtils.deleteWhitespace(telefone), "("), ")"), "-"));
	}

}
