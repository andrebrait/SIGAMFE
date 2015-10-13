package com.sigamfe.model.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.sigamfe.model.Usuario;
import com.sigamfe.model.converter.LocalDateTimeConverter;

import lombok.Data;
import lombok.ToString;

/**
 * Classe BaseEntity. Qualquer entidade do sistema DEVE herdar desta classe
 * abstrata se tiver propriedades de auditoria.
 *
 * @param <I>
 *            O tipo do ID da entidade
 */
@Data
@ToString(callSuper = false, of = { "dataAtualizacao", "dataCriacao" })
@MappedSuperclass
public abstract class AuditableBaseEntity<ID extends Serializable> implements BaseEntity<ID> {

	private static final long serialVersionUID = -5479406946881675009L;

	@LastModifiedDate
	@Convert(converter = LocalDateTimeConverter.class)
	protected LocalDateTime dataAtualizacao;

	@NotNull
	@CreatedDate
	@Convert(converter = LocalDateTimeConverter.class)
	protected LocalDateTime dataCriacao;

	public abstract Usuario getUsuarioAtualizacao();

	public abstract Usuario getUsuarioCriacao();

}
