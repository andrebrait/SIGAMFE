package com.sigamfe.model.enums;

import com.sigamfe.model.enums.interfaces.PersistentEnum;

import lombok.Getter;

/**
 * Enum IndicadorSN. Usado para indicar estados de SIM/NÃO.
 */
public enum IndicadorSN implements PersistentEnum {

	/** Indicador SIM */
	SIM("S", "Sim"),
	/** Indicador NAO */
	NAO("N", "Não");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private IndicadorSN(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}

}
