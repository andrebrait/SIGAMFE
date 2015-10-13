package com.sigamfe.model.enums;

import com.sigamfe.model.enums.interfaces.PersistentEnum;

import lombok.Getter;

/**
 * Enum PermissaoUsuario. Indica a permissão do Usuário. Inclui o indicador de
 * papel usado pelo Spring Security.
 */
public enum PermissaoUsuario implements PersistentEnum {

	/**
	 * Vendedor
	 */
	VENDEDOR("ROLE_USER", "V", "Vendedor"),
	/**
	 * Administrador
	 */
	ADMINISTRADOR("ROLE_ADMIN", "A", "Administrador");

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
