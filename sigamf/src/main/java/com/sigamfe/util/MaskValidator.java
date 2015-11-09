package com.sigamfe.util;

import org.apache.commons.lang3.StringUtils;

public class MaskValidator {

	public static final String CPF_MASK = "999.999.999-99";
	public static final String TELEFONE_8_DIGITOS_MASK = "(99)9999-9999";
	public static final String TELEFONE_9_DIGITOS_MASK = "(99)99999-9999";
	public static final String CNPJ_MASK = "999.999.999/9999-99";
	public static final String CEP_MASK = "99999-999";
	public static final String RG_SHORT_MASK = "A-99.999.999";
	public static final String RG_LONG_MASK = "AA-99.999.999";
	public static final String CNH_MASK = "99999999999";

	private static interface MaskValidatorCallback {

		public abstract String validate(String str);

	}

	/**
	 * Retorna a versão apropriada do validador de acordo com o comprimento da
	 * String de valor nova.
	 *
	 * @param newValue
	 *            the new value
	 * @param oldValue
	 *            the old value
	 * @param shortValidator
	 *            the short version
	 * @param longValidator
	 *            the long version
	 * @return the version by length
	 */
	public static String getVersionByLength(String newValue, String oldValue, MaskValidator shortValidator,
			MaskValidator longValidator) {
		return StringUtils.length(newValue) > shortValidator.length() ? longValidator.validate(newValue, oldValue)
				: shortValidator.validate(newValue, oldValue);
	}

	/**
	 * Retorna a versão apropriada do validador de acordo com o próximo char
	 * inserido (útil para casos como RG, que podem ser tanto A-99.999.999
	 * quanto AA-99.999.999).
	 *
	 * @param newValue
	 *            the new value
	 * @param oldValue
	 *            the old value
	 * @param type1
	 *            the type1
	 * @param type2
	 *            the type2
	 * @return the version by inserted char
	 */
	public static String getVersionByInsertedChar(String newValue, String oldValue, MaskValidator type1,
			MaskValidator type2) {
		String type1str = type1.validate(newValue, oldValue);
		String type2str = type2.validate(newValue, oldValue);
		return StringUtils.length(type1str) > StringUtils.length(type2str) ? type1str : type2str;
	}

	private MaskValidatorCallback[] validators;

	private final boolean autocapitalize;

	/**
	 * Instancia um novo MaskValidator. Nota: a máscara não pode ser nula! A
	 * máscara deve conter o formato:
	 * <ul>
	 * <li>? para qualquer símbolo</li>
	 * <li>L para qualquer letra (ASCII)</li>
	 * <li>A para letras maiúsculas (ASCII)</li>
	 * <li>a para letras minúsculas (ASCII)</li>
	 * <li>O para qualquer letra (UNICODE)</li>
	 * <li>U para letras maiúsculas (UNICODE)</li>
	 * <li>u para letras minúsculas (UNICODE)</li>
	 * <li>X para qualquer símbolo alfanumérico (ASCII)</li>
	 * <li>Y para qualquer símbolo alfanumérico (UNICODE)</li>
	 * <li>9 para símbolos numéricos de 0 a 9</li>
	 * <li>outros numerais x para valores de 0 a x</li>
	 * </ul>
	 *
	 * @param mask
	 *            the mask
	 */
	public MaskValidator(String mask) {
		this(mask, false);
	}

	/**
	 * Instancia um novo MaskValidator. Nota: a máscara não pode ser nula! A
	 * máscara deve conter o formato:
	 * <ul>
	 * <li>? para qualquer símbolo</li>
	 * <li># para qualquer símbolo (exceto espaços)</li>
	 * <li>L para qualquer letra (ASCII)</li>
	 * <li>A para letras maiúsculas (ASCII)</li>
	 * <li>a para letras minúsculas (ASCII)</li>
	 * <li>O para qualquer letra (UNICODE)</li>
	 * <li>U para letras maiúsculas (UNICODE)</li>
	 * <li>u para letras minúsculas (UNICODE)</li>
	 * <li>X para qualquer símbolo alfanumérico (ASCII)</li>
	 * <li>Y para qualquer símbolo alfanumérico (UNICODE)</li>
	 * <li>9 para símbolos numéricos de 0 a 9</li>
	 * <li>outros numerais x para valores de 0 a x</li>
	 * </ul>
	 *
	 * Adicionalmente, este construtor permite ativar a opção de
	 * autocapitalização, isto é, se a máscara deve converter automaticamente
	 * maiúsculos e minúsculos para que estes se adaptem ao filtro.
	 *
	 * @param mask
	 *            the mask
	 * @param autocapitalize
	 *            the autocapitalize
	 */
	public MaskValidator(String mask, final boolean autocapitalize) {
		this.autocapitalize = autocapitalize;
		if (StringUtils.isNotBlank(mask)) {
			char[] chars = mask.toCharArray();
			validators = new MaskValidatorCallback[chars.length];
			for (int i = 0; i < chars.length; i++) {
				char ch = chars[i];
				if (ch == '?') {
					validators[i] = str -> StringUtils.isNotEmpty(str) ? str : null;
				} else if (ch == '#') {
					validators[i] = str -> StringUtils.isNotBlank(str) ? str : null;
				} else if (ch == 'L') {
					validators[i] = str -> StringUtils.isNotBlank(str) && str.matches("[a-zA-Z]") ? str : null;
				} else if (ch == 'A') {
					validators[i] = str -> {
						if (this.autocapitalize && str != null) {
							str = str.toUpperCase();
						}
						return StringUtils.isNotBlank(str) && str.matches("[A-Z]") ? str : null;
					};
				} else if (ch == 'a') {
					validators[i] = str -> {
						if (this.autocapitalize && str != null) {
							str = str.toLowerCase();
						}
						return StringUtils.isNotBlank(str) && str.matches("[a-z]") ? str : null;
					};
				} else if (ch == 'O') {
					validators[i] = str -> StringUtils.isNotBlank(str) && str.matches("\\p{L}") ? str : null;
				} else if (ch == 'U') {
					validators[i] = str -> {
						if (this.autocapitalize && str != null) {
							str = str.toUpperCase();
						}
						return StringUtils.isNotBlank(str) && str.matches("\\p{Lu}") ? str : null;
					};
				} else if (ch == 'u') {
					validators[i] = str -> {
						if (this.autocapitalize && str != null) {
							str = str.toLowerCase();
						}
						return StringUtils.isNotBlank(str) && str.matches("\\p{Ll}") ? str : null;
					};
				} else if (ch == 'X') {
					validators[i] = str -> StringUtils.isNotBlank(str) && str.matches("[a-zA-Z0-9]") ? str : null;
				} else if (ch == 'Y') {
					validators[i] = str -> StringUtils.isNotBlank(str) && str.matches("\\p{L}|\\p{Nd}") ? str : null;
				} else {
					String charRep = String.valueOf(ch);
					if (charRep.matches("[0-9]")) {
						validators[i] = str -> StringUtils.isNotBlank(str) && str.matches("\\p{Nd}")
								&& Integer.parseInt(str) <= Integer.parseInt(charRep) ? str : null;
					} else {
						final char separator = ch;
						validators[i] = str -> String.valueOf(separator);
					}
				}
			}
		}
	}

	public int length() {
		return validators.length;
	}

	private boolean equalIgnoreCaseIfAutocapitalize(String newValue, String oldValue) {
		if (autocapitalize) {
			return newValue.equalsIgnoreCase(oldValue);
		}
		return newValue.equals(oldValue);
	}

	public String validate(String newValue, String oldValue) {
		if (StringUtils.isNotBlank(newValue)) {
			if (!equalIgnoreCaseIfAutocapitalize(newValue, oldValue)) {
				newValue = TextFieldUtils.processMaxChars(newValue, oldValue, length());
				char[] textChars = newValue.toCharArray();
				int i = 0, j = 0;
				StringBuilder sb = new StringBuilder();
				while (i < textChars.length && j < length()) {
					String str = String.valueOf(textChars[i]);
					String toInsert = validators[j].validate(str);
					if (toInsert == null) {
						i++;
					} else {
						sb.append(toInsert);
						if (!equalIgnoreCaseIfAutocapitalize(toInsert, str)) {
							j++;
						} else {
							i++;
							j++;
						}
					}
				}
				return sb.toString();
			} else {
				return StringUtils.isBlank(newValue) ? StringUtils.EMPTY : newValue;
			}
		}
		return StringUtils.isBlank(newValue) ? StringUtils.EMPTY : oldValue;
	}

}
