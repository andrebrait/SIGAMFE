package com.sigamfe.model.enums;

import com.sigamfe.model.enums.interfaces.PersistentEnum;

import lombok.Getter;

/**
 * Enum FormaPagamento. Indica a forma do pagamento adotada no pedido.
 */
public enum FormaPagamento implements PersistentEnum {

	/**
	 * Pagamento em dinheiro
	 */
	DINHEIRO("DI", "Dinheiro"),
	/**
	 * Pagamento em Cartão de Credito
	 */
	CARTAO_CREDITO("CC", "Cartão de Crédito"),
	/**
	 * Pagamento em Cartão de Débito
	 */
	CARTAO_DEBITO("CD", "Cartão de Débito"),
	/**
	 * Pagamento em Cheque.
	 */
	CHEQUE("CH", "Cheque");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private FormaPagamento(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}

}
