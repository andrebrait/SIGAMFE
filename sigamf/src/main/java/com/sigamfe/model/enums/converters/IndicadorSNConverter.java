package com.sigamfe.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sigamfe.exception.SIGAMFException;
import com.sigamfe.model.enums.IndicadorSN;

@Converter(autoApply = true)
public class IndicadorSNConverter implements AttributeConverter<IndicadorSN, String> {

	@Override
	public String convertToDatabaseColumn(IndicadorSN attribute) {
		return attribute.getCodigo();
	}

	@Override
	public IndicadorSN convertToEntityAttribute(String dbData) {
		for (IndicadorSN ind : IndicadorSN.values()) {
			if (ind.getCodigo().equals(dbData)) {
				return ind;
			}
		}
		throw new SIGAMFException(new IllegalArgumentException(dbData + "não é um valor conhecido"));
	}

}