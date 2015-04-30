package com.sigamfe.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sigamfe.exception.EnumException;
import com.sigamfe.model.enums.PermissaoUsuario;

@Converter(autoApply = true)
public class PermissaoUsuarioConverter implements AttributeConverter<PermissaoUsuario, String> {

	@Override
	public String convertToDatabaseColumn(PermissaoUsuario attribute) {
		return attribute.getCodigo();
	}

	@Override
	public PermissaoUsuario convertToEntityAttribute(String dbData) {
		for (final PermissaoUsuario perm : PermissaoUsuario.values()) {
			if (perm.getCodigo().equals(dbData)) {
				return perm;
			}
		}
		throw new EnumException(dbData);
	}

}
