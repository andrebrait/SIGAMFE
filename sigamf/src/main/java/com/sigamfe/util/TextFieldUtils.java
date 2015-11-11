package com.sigamfe.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;

public class TextFieldUtils {

	public static String processRepeatedSpaces(String newValue, String oldValue) {
		String returnStr = oldValue;
		if (StringUtils.isNotEmpty(newValue) && !newValue.equals(oldValue)) {
			returnStr = StringUtils.normalizeSpace(newValue);
		}
		return returnStr;
	}

	public static String processMaxChars(String newValue, int max) {
		if (newValue == null) {
			return null;
		}
		while (newValue.length() > max) {
			newValue = StringUtils.chop(newValue);
		}
		return newValue;
	}

	/**
	 * Processa o field para ajustar à máscara fornecida.
	 *
	 * @param textField
	 *            the text field
	 * @param mask
	 *            the mask
	 */
	public static String processMask(String newValue, String oldValue, MaskValidator mask) {
		return mask.validate(newValue, oldValue);
	}

	public static String processMaxDecimal(String newValue, String oldValue, int minIntegerDigits, int maxIntegerDigits,
			int fractionDigits) {
		if (StringUtils.isNotBlank(newValue) && !newValue.equals(oldValue)) {
			String toLongNum = StringUtils.remove(StringUtils.remove(newValue, ","), ".");
			toLongNum = processMaxChars(toLongNum, maxIntegerDigits + fractionDigits);
			if (!StringUtils.isNumeric(toLongNum)) {
				return oldValue;
			}
			BigDecimal num = new BigDecimal(toLongNum);
			num.setScale(fractionDigits, RoundingMode.HALF_UP);
			if (fractionDigits != 0) {
				num = num.divide(new BigDecimal(Math.pow(10, fractionDigits)));
			}
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMinimumFractionDigits(fractionDigits);
			nf.setMaximumFractionDigits(fractionDigits);
			nf.setMinimumIntegerDigits(minIntegerDigits);
			nf.setMaximumIntegerDigits(maxIntegerDigits);
			return num.compareTo(BigDecimal.ZERO) != 0 ? nf.format(num) : StringUtils.EMPTY;
		}
		return StringUtils.isBlank(newValue) ? StringUtils.EMPTY : oldValue;
	}

}
