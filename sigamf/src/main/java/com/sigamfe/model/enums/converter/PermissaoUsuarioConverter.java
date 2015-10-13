package com.sigamfe.model.enums.converter;

import javax.persistence.Converter;

import com.sigamfe.model.enums.PermissaoUsuario;
import com.sigamfe.model.enums.converter.base.PersistentEnumConverter;

@Converter(autoApply = true)
public class PermissaoUsuarioConverter extends PersistentEnumConverter<PermissaoUsuario> {

	public PermissaoUsuarioConverter() {
		super(PermissaoUsuario.class);
	}
}