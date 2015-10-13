package com.sigamfe.model.enums.converter;

import javax.persistence.Converter;

import com.sigamfe.model.enums.TipoMovimentoEstoque;
import com.sigamfe.model.enums.converter.base.PersistentEnumConverter;

@Converter(autoApply = true)
public class TipoMovimentoEstoqueConverter extends PersistentEnumConverter<TipoMovimentoEstoque> {

	public TipoMovimentoEstoqueConverter() {
		super(TipoMovimentoEstoque.class);
	}

}
