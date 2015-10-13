package com.sigamfe.model.enums.interfaces;

/**
 * Interface PersistentEnum. Métodos comuns a todos os Enums Persistentes do
 * sistema.
 */
public interface PersistentEnum {

	/**
	 * Retorna o código (valor persistido do enum) para o banco.
	 *
	 * @return o código
	 */
	public String getCodigo();

	/**
	 * Retorna o label (valor de exibição na interface) do enum.
	 *
	 * @return o label
	 */
	public String getLabel();
}
