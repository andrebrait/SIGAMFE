package com.sigamfe.model.enums.converter;

import javax.persistence.Converter;

import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.converter.base.PersistentEnumConverter;

@Converter(autoApply = true)
public class IndicadorSNConverter extends PersistentEnumConverter<IndicadorSN> {

	public IndicadorSNConverter() {
		super(IndicadorSN.class);
	}
}