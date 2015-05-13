package com.sigamfe.model.enums;

import lombok.Getter;

public enum EstadoPedido {

	ABERTO("AB", "Aberto"), PAGO("PG", "Pago"), PENDENCIA_ALUGUEL("PA", "Pendência (aluguel)"), PENDENCIA("PE", "Pendência"), REPOSICAO("RP",
			"Reposição"), PENDENCIA_TOTAL("PT", "Pendência (total)"), FECHADO("FE", "Fechado");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private EstadoPedido(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}

}
