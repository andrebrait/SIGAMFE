package com.sigamfe.model.base;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class BaseEntity.
 *
 * @param <I>
 *            O tipo do ID
 */
public abstract class BaseEntity<I> implements Serializable {

	private static final long serialVersionUID = -5479406946881675009L;

	public abstract I getId();

	public abstract void setId(I id);

	public abstract Date getDataCriacao();

	public abstract Date getDataAtualizacao();

}
