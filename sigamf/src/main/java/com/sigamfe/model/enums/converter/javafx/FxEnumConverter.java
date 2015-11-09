package com.sigamfe.model.enums.converter.javafx;

import org.apache.commons.lang3.StringUtils;

import com.sigamfe.exception.EnumException;
import com.sigamfe.model.enums.IndicadorUnidade;
import com.sigamfe.model.enums.interfaces.PersistentEnum;

import javafx.util.StringConverter;

public class FxEnumConverter<T extends Enum<T> & PersistentEnum> extends StringConverter<T> {

	private Class<T> clazz;

	public String getStringToShow(T object) {
		return object instanceof IndicadorUnidade ? ((IndicadorUnidade) object).getAbreviacao() : object.getLabel();
	}

	public FxEnumConverter(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public String toString(T object) {
		if (object != null) {
			return getStringToShow(object);
		}
		return StringUtils.EMPTY;
	}

	@Override
	public T fromString(String string) {
		for (T ind : clazz.getEnumConstants()) {
			if (getStringToShow(ind).equals(string)) {
				return ind;
			}
		}
		throw new EnumException(string);
	}

}
