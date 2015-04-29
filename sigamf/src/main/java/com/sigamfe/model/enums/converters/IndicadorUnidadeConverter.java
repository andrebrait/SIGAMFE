package com.sigamfe.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sigamfe.exception.SIGAMFException;
import com.sigamfe.model.enums.IndicadorUnidade;

@Converter(autoApply = true)
public class IndicadorUnidadeConverter implements AttributeConverter<IndicadorUnidade, String> {

	@Override
	public String convertToDatabaseColumn(IndicadorUnidade attribute) {
		return attribute.getCodigo();
	}

	@Override
	public IndicadorUnidade convertToEntityAttribute(String dbData) {
		for (IndicadorUnidade ind : IndicadorUnidade.values()) {
			if (ind.getCodigo().equals(dbData)) {
				return ind;
			}
		}
		throw new SIGAMFException(new IllegalArgumentException(dbData + "não é um valor conhecido"));
	}
}