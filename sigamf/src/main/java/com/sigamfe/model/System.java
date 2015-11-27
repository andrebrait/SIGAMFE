package com.sigamfe.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Configurable;

import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.converter.LocalDateTimeConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Configurable
@Entity
@Data
@Table(name = "system")
@EqualsAndHashCode(callSuper = false, of = "id")
public class System implements BaseEntity<Byte> {

	private static final long serialVersionUID = 7797452984436492733L;

	@Id
	@Column(name = "ID")
	private Byte id;

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "ULTIMAVEZATUALIZOUPEDIDO", nullable = true)
	private LocalDateTime ultimaVezAtualizouPedido;

	@Override
	public Long getVersion() {
		return null;
	}

	@Override
	public void setVersion(Long version) {

	}

}
