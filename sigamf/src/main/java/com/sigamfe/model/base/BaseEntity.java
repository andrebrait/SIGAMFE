package com.sigamfe.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.ToString;

/**
 * The Class BaseEntity.
 *
 * @param <I>
 *            O tipo do ID
 */
@Data
@ToString
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -5479406946881675009L;

	protected Date dataAtualizacao;

	protected Date dataCriacao;

}
