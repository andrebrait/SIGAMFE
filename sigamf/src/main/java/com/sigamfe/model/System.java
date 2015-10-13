package com.sigamfe.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.converter.LocalDateTimeConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "system")
@EqualsAndHashCode(callSuper = false, of = "id")
public class System implements BaseEntity<Short> {

	private static final long serialVersionUID = 7797452984436492733L;

	@Id
	@Column(name = "ID")
	private Short id;

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "ULTIMAVEZATUALIZOUPEDIDO", nullable = true)
	private LocalDateTime ultimaVezAtualizouPedido;

}
