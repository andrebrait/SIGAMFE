package com.sigamfe.model.enums;

import lombok.Getter;

/**
 * The Enum IndicadorSN.
 */
public enum IndicadorSN {

	SIM("S", "Sim"), NAO("N", "Não");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private IndicadorSN(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}

}
