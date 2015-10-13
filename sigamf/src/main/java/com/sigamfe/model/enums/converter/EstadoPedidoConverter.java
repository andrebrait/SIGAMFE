package com.sigamfe.model.enums.converter;

import javax.persistence.Converter;

import com.sigamfe.model.enums.EstadoPedido;
import com.sigamfe.model.enums.converter.base.PersistentEnumConverter;

@Converter(autoApply = true)
public class EstadoPedidoConverter extends PersistentEnumConverter<EstadoPedido> {

	public EstadoPedidoConverter() {
		super(EstadoPedido.class);
	}
}