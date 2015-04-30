package com.sigamfe.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sigamfe.exception.EnumException;
import com.sigamfe.model.enums.EntregaPedido;

@Converter(autoApply = true)
public class EntregaPedidoConverter implements AttributeConverter<EntregaPedido, String> {

	@Override
	public String convertToDatabaseColumn(EntregaPedido attribute) {
		return attribute.getCodigo();
	}

	@Override
	public EntregaPedido convertToEntityAttribute(String dbData) {
		for (final EntregaPedido ind : EntregaPedido.values()) {
			if (ind.getCodigo().equals(dbData)) {
				return ind;
			}
		}
		throw new EnumException(dbData);
	}
}