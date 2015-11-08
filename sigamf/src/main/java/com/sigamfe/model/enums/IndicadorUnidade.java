package com.sigamfe.model.enums;

import com.sigamfe.model.enums.interfaces.PersistentEnum;

import lombok.Getter;

/**
 * Enum IndicadorUnidade. Indica a unidade em que o material será considerado no
 * sistema.
 */
public enum IndicadorUnidade implements PersistentEnum {

	/** Unidade */
	UNIDADE("UN", "Unidade", "un."),
	/**
	 * Dúzia
	 */
	DUZIA("DZ", "Dúzia", "dz.");

	@Getter
	private String codigo;

	@Getter
	private String label;

	@Getter
	private String abreviacao;

	private IndicadorUnidade(String codigo, String label, String abreviacao) {
		this.codigo = codigo;
		this.label = label;
		this.abreviacao = abreviacao;
	}
}
