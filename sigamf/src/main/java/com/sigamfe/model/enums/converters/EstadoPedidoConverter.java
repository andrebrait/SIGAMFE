package com.sigamfe.model.enums.converters;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sigamfe.exception.EnumException;
import com.sigamfe.model.enums.EstadoPedido;

@Converter(autoApply = true)
public class EstadoPedidoConverter implements AttributeConverter<EstadoPedido, String> {

	@Override
	public String convertToDatabaseColumn(EstadoPedido attribute) {
		return Optional.ofNullable(attribute).map(EstadoPedido::getCodigo).orElse(null);
	}

	@Override
	public EstadoPedido convertToEntityAttribute(String dbData) {
		for (EstadoPedido ind : EstadoPedido.values()) {
			if (ind.getCodigo().equals(dbData)) {
				return ind;
			}
		}
		throw new EnumException(dbData);
	}
}