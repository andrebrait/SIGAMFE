package com.sigamfe.model.enums.converters;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sigamfe.exception.EnumException;
import com.sigamfe.model.enums.IndicadorSN;

@Converter(autoApply = true)
public class IndicadorSNConverter implements AttributeConverter<IndicadorSN, String> {

	@Override
	public String convertToDatabaseColumn(IndicadorSN attribute) {
		return Optional.ofNullable(attribute).map(IndicadorSN::getCodigo).orElse(null);
	}

	@Override
	public IndicadorSN convertToEntityAttribute(String dbData) {
		for (IndicadorSN ind : IndicadorSN.values()) {
			if (ind.getCodigo().equals(dbData)) {
				return ind;
			}
		}
		throw new EnumException(dbData);
	}

}