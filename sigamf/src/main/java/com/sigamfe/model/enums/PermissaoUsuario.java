package com.sigamfe.model.enums;

import lombok.Getter;

public enum PermissaoUsuario {

	VENDEDOR("ROLE_USER", "Vendedor"), ADMINISTRADOR("ROLE_ADMIN", "Administrador");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private PermissaoUsuario(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}
}
