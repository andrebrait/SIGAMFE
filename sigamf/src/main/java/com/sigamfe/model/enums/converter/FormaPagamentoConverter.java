package com.sigamfe.model.enums.converter;

import javax.persistence.Converter;

import com.sigamfe.model.enums.FormaPagamento;
import com.sigamfe.model.enums.converter.base.PersistentEnumConverter;

@Converter(autoApply = true)
public class FormaPagamentoConverter extends PersistentEnumConverter<FormaPagamento> {

	public FormaPagamentoConverter() {
		super(FormaPagamento.class);
	}
}