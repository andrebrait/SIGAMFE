package com.sigamfe.model.enums;

import lombok.Getter;

public enum EntregaPedido {

	MANHA("M", "Manhã"), TARDE("T", "Tarde"), RETIRADA("R", "Retirada");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private EntregaPedido(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}
}
