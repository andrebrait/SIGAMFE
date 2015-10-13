package com.sigamfe.model.enums;

import com.sigamfe.model.enums.interfaces.PersistentEnum;

import lombok.Getter;

/**
 * Enum IndicadorUnidade. Indica a unidade em que o material será considerado no
 * sistema.
 */
public enum IndicadorUnidade implements PersistentEnum {

	/** Unidade */
	UNIDADE("UN", "Unidade"),
	/**
	 * Dúzia
	 */
	DUZIA("DZ", "Dúzia");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private IndicadorUnidade(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}
}
