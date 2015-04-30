package com.sigamfe.model.enums;

import lombok.Getter;

public enum FormaPagamento {

	DINHEIRO("DI", "Dinheiro"), CREDITO("CC", "Cartão de Crédito"), DEBITO("CD", "Cartão de Débito"), CHEQUE("CH", "Cheque");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private FormaPagamento(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}

}
