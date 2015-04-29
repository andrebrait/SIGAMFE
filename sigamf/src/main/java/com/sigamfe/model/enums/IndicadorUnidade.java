package com.sigamfe.model.enums;

import lombok.Getter;

/**
 * The Enum IndicadorUnidade.
 */
public enum IndicadorUnidade {

	UNIDADE("UN", "Unidade"), DUZIA("DZ", "Dúzia");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private IndicadorUnidade(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}
}
