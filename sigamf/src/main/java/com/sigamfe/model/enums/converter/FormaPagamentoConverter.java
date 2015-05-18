package com.sigamfe.model.enums.converter;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sigamfe.exception.EnumException;
import com.sigamfe.model.enums.FormaPagamento;

@Converter(autoApply = true)
public class FormaPagamentoConverter implements AttributeConverter<FormaPagamento, String> {

	@Override
	public String convertToDatabaseColumn(FormaPagamento attribute) {
		return Optional.ofNullable(attribute).map(FormaPagamento::getCodigo).orElse(null);
	}

	@Override
	public FormaPagamento convertToEntityAttribute(String dbData) {
		for (FormaPagamento ind : FormaPagamento.values()) {
			if (ind.getCodigo().equals(dbData)) {
				return ind;
			}
		}
		throw new EnumException(dbData);
	}
}