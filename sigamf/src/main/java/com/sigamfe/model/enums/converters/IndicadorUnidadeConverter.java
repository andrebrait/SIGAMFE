package com.sigamfe.model.enums.converters;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sigamfe.exception.EnumException;
import com.sigamfe.model.enums.IndicadorUnidade;

@Converter(autoApply = true)
public class IndicadorUnidadeConverter implements AttributeConverter<IndicadorUnidade, String> {

	@Override
	public String convertToDatabaseColumn(IndicadorUnidade attribute) {
		return Optional.ofNullable(attribute).map(IndicadorUnidade::getCodigo).orElse(null);
	}

	@Override
	public IndicadorUnidade convertToEntityAttribute(String dbData) {
		for (IndicadorUnidade ind : IndicadorUnidade.values()) {
			if (ind.getCodigo().equals(dbData)) {
				return ind;
			}
		}
		throw new EnumException(dbData);
	}
}