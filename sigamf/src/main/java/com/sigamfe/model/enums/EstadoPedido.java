package com.sigamfe.model.enums;

import lombok.Getter;

public enum EstadoPedido {

	ABERTO("A", "Aberto"), PAGO("P", "Pago"), IRREGULAR("I", "Irregular"), FECHADO("F", "Fechado");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private EstadoPedido(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}

}
