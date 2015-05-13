package com.sigamfe.model.enums;

import lombok.Getter;

public enum PermissaoUsuario {

	VENDEDOR("ROLE_USER", "V", "Vendedor"), ADMINISTRADOR("ROLE_ADMIN", "A", "Administrador");

	@Getter
	private String codigoSpringSecurity;

	@Getter
	private String codigo;

	@Getter
	private String label;

	private PermissaoUsuario(String codigoSpringSecurity, String codigo, String label) {
		this.codigoSpringSecurity = codigoSpringSecurity;
		this.codigo = codigo;
		this.label = label;
	}
}
