package com.sigamfe.model.enums;

import com.sigamfe.model.enums.interfaces.PersistentEnum;

import lombok.Getter;

/**
 * Enum EstadoPedido. Indica o estado atual do pedido. Os estados de pendência
 * permanecem valendo, cumulativamente, até que um estado OK equivalente seja
 * colocado no pedido.
 */
public enum EstadoPedido implements PersistentEnum {

	/**
	 * O pedido está aberto.
	 */
	ABERTO("AB", "Aberto"),
	/**
	 * A entrega do pedido está pendente.
	 */
	ENTREGA_PENDENTE("EP", "Entrega pendente"),
	/**
	 * A devolução do pedido está pendente.
	 */
	DEVOLUCAO_PENDENTE("DP", "Devolução pendente"),
	/**
	 * Reposição de itens do pedido está ok.
	 */
	REPOSICAO_PENDENTE("RP", "Reposição pendente"),
	/**
	 * Pagamento (parcial ou total) pendente.
	 */
	PAGAMENTO_PENDENTE("PP", "Pagamento pendente"),
	/**
	 * Entrega do pedido ao cliente ok.
	 */
	ENTREGA_OK("EO", "Entregue"),
	/**
	 * Devolução do pedido pelo cliente ok.
	 */
	DEVOLUCAO_OK("DO", "Devolvido"),
	/**
	 * Reposição de itens do pedido ok.
	 */
	REPOSICAO_OK("RO", "Devolvido"),
	/**
	 * Pagamento do pedido em dia ou quitado (a ser determinado na aplicação).
	 */
	PAGAMENTO_OK("PO", "Pagamento em dia"),
	/**
	 * Pedido fechado.
	 */
	FECHADO("FE", "Fechado");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private EstadoPedido(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}

}
