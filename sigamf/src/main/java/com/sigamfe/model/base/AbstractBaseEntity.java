package com.sigamfe.model.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.sigamfe.model.converter.LocalDateTimeConverter;

/**
 * The Class BaseEntity.
 *
 * @param <I>
 *            O tipo do ID
 */
@Data
@MappedSuperclass
public abstract class AbstractBaseEntity<ID extends Serializable> implements Serializable, BaseEntity<ID> {

	private static final long serialVersionUID = -5479406946881675009L;

	@LastModifiedDate
	@Convert(converter = LocalDateTimeConverter.class)
	protected LocalDateTime dataAtualizacao;

	@NotNull
	@CreatedDate
	@Convert(converter = LocalDateTimeConverter.class)
	protected LocalDateTime dataCriacao;

}
