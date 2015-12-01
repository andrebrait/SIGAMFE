package com.sigamfe.model.enums.converter.javafx;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import javafx.util.StringConverter;

public class FxBigDecimalConverter extends StringConverter<BigDecimal> {

	@Override
	public String toString(BigDecimal object) {
		if (object == null) {
			return null;
		}
		return DecimalFormat.getInstance().format(object);
	}

	@Override
	public BigDecimal fromString(String string) {
		if (StringUtils.isBlank(string)) {
			return null;
		}
		try {
			DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
			df.setParseBigDecimal(true);
			return (BigDecimal) df.parse(string);
		} catch (ParseException e) {
			return null;
		}
	}

}
