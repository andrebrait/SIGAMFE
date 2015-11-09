package com.sigamfe.util;

import org.apache.commons.lang3.StringUtils;

public class MaskValidator {

	public static final String CPF_MASK = "999.999.999-99";
	public static final String TELEFONE_8_DIGITOS_MASK = "(99)9999-9999";
	public static final String TELEFONE_9_DIGITOS_MASK = "(99)99999-9999";
	public static final String CNPJ_MASK = "999.999.999/9999-99";
	public static final String CEP_MASK = "99999-999";
	public static final String RG_MASK = "AA-999.999.999";

	private static interface MaskValidatorCallback {

		public abstract String validate(String str);

	}

	private MaskValidatorCallback[] validators;

	/**
	 * Instancia um novo MaskValidator. Nota: a máscara não pode ser nula! A
	 * máscara deve conter o formato ? para qualquer símbolo, 9 para símbolos
	 * numéricos e A para letras.
	 *
	 * Ex.: máscaras como AAA/AAA/AA/?? permitem coisas como abc/def/gh/3B
	 *
	 * @param mask
	 *            the mask
	 */
	public MaskValidator(String mask) {
		if (StringUtils.isNotBlank(mask)) {
			char[] chars = mask.toCharArray();
			validators = new MaskValidatorCallback[chars.length];
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] == 'A') {
					validators[i] = str -> StringUtils.isNumeric(str) || StringUtils.isBlank(str) ? null : str;
				} else if (chars[i] == '9') {
					validators[i] = str -> StringUtils.isNumeric(str) ? str : null;
				} else if (chars[i] == '?') {
					validators[i] = str -> StringUtils.isNotBlank(str) ? str : null;
				} else {
					final char separator = chars[i];
					validators[i] = str -> String.valueOf(separator);
				}
			}
		}
	}

	public int length() {
		return validators.length;
	}

	public String validate(String newValue, String oldValue) {
		if (StringUtils.isNotBlank(newValue) && !newValue.equals(oldValue)) {
			newValue = TextFieldUtils.processMaxChars(newValue, oldValue, length());
			char[] textChars = newValue.toCharArray();
			int i = textChars.length - 1;
			int j = length() - 1;
			StringBuilder sb = new StringBuilder();
			while (i >= 0 && j >= 0) {
				String str = String.valueOf(textChars[i]);
				String toInsert = validators[j].validate(str);
				if (toInsert == null) {
					i--;
				} else {
					sb.insert(0, toInsert);
					if (!toInsert.equals(str)) {
						j--;
					} else {
						i--;
						j--;
					}
				}
			}
			return sb.toString();
		}
		return StringUtils.isBlank(newValue) ? StringUtils.EMPTY : oldValue;
	}

}
