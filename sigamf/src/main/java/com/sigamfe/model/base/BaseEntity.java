package com.sigamfe.model.base;

import java.io.Serializable;

/**
 * A Interface BaseEntity. Todas as entidades do sistema DEVEM implementar esta
 * interface.
 *
 * @param <ID>
 *            O tipo do ID da entidade
 */
public interface BaseEntity<ID extends Serializable> extends Serializable {

	/**
	 * Retorna o ID da entidade
	 *
	 * @return o id
	 */
	public abstract ID getId();

	/**
	 * Atribui o ID à entidade.
	 *
	 * @param id
	 *            O novo ID.
	 */
	public abstract void setId(ID id);

	/**
	 * Retorna a versão da entidade
	 *
	 * @return A versão
	 */
	public abstract Long getVersion();

	/**
	 * Atribui a versão à entidade
	 *
	 * @param version
	 *            a nova versão
	 */
	public abstract void setVersion(Long version);

}
