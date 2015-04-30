package com.sigamfe.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sigamfe.exception.EnumException;
import com.sigamfe.model.enums.EstadoPedido;

@Converter(autoApply = true)
public class EstadoPedidoConverter implements AttributeConverter<EstadoPedido, String> {

	@Override
	public String convertToDatabaseColumn(EstadoPedido attribute) {
		return attribute.getCodigo();
	}

	@Override
	public EstadoPedido convertToEntityAttribute(String dbData) {
		for (final EstadoPedido ind : EstadoPedido.values()) {
			if (ind.getCodigo().equals(dbData)) {
				return ind;
			}
		}
		throw new EnumException(dbData);
	}
}