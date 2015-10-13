package com.sigamfe.model.enums;

import com.sigamfe.model.enums.interfaces.PersistentEnum;

import lombok.Getter;

/**
 * Enum EntregaPedido. Indica o turno da entrega do pedido, ou se este deverá
 * ser retirado pelo cliente na loja.
 */
public enum EntregaPedido implements PersistentEnum {

	/** Pedido a ser entregue no turno da manhã. */
	MANHA("M", "Manhã"),
	/** Pedido a ser entregue no turno da tarde. */
	TARDE("T", "Tarde"),
	/** Pedido a ser retirado na loja, pelo cliente. */
	RETIRADA("R", "Retirada");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private EntregaPedido(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}
}
