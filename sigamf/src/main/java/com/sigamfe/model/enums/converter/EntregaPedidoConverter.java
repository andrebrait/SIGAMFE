package com.sigamfe.model.enums.converter;

import javax.persistence.Converter;

import com.sigamfe.model.enums.EntregaPedido;
import com.sigamfe.model.enums.converter.base.PersistentEnumConverter;

/**
 * Classe EntregaPedidoConverter.
 */
@Converter(autoApply = true)
public class EntregaPedidoConverter extends PersistentEnumConverter<EntregaPedido> {

	public EntregaPedidoConverter() {
		super(EntregaPedido.class);
	}
}