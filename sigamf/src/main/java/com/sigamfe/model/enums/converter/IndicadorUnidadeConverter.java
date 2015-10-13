package com.sigamfe.model.enums.converter;

import javax.persistence.Converter;

import com.sigamfe.model.enums.IndicadorUnidade;
import com.sigamfe.model.enums.converter.base.PersistentEnumConverter;

@Converter(autoApply = true)
public class IndicadorUnidadeConverter extends PersistentEnumConverter<IndicadorUnidade> {

	public IndicadorUnidadeConverter() {
		super(IndicadorUnidade.class);
	}
}