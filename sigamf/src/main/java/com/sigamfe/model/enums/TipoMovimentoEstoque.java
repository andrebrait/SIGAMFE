package com.sigamfe.model.enums;

import com.sigamfe.model.enums.interfaces.PersistentEnum;

import lombok.Getter;

/**
 * Enum TipoMovimentoEstoque. Indica o tipo de movimentação que ocorreu no
 * estoque.
 */
public enum TipoMovimentoEstoque implements PersistentEnum {

	/**
	 * Compra
	 */
	COMPRA("C", "Compra"),
	/**
	 * Quebra
	 */
	QUEBRA("Q", "Quebra"),
	/**
	 * Reposição
	 */
	REPOSICAO("R", "Reposição"),
	/**
	 * Aluguel
	 */
	ALUGUEL("A", "Aluguel"),
	/**
	 * Devolução
	 */
	DEVOLUCAO("D", "Devolução");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private TipoMovimentoEstoque(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}
}
