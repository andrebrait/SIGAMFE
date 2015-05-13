package com.sigamfe.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.data.annotation.CreatedDate;

import com.sigamfe.model.base.LocalDateTimeConverter;
import com.sigamfe.model.enums.EstadoPedido;
import com.sigamfe.model.enums.converters.EstadoPedidoConverter;

@Entity
@Table(name = "pedidopendencia")
@Data
@ToString(callSuper = true, exclude = "pedido")
@EqualsAndHashCode(callSuper = false, of = "id")
public class PedidoPendencia implements Serializable {

	private static final long serialVersionUID = -399787981979837915L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "IDPEDIDO", nullable = false)
	private Pedido pedido;

	@NotNull
	@Convert(converter = EstadoPedidoConverter.class)
	@Column(name = "ESTADO", nullable = false, length = 2)
	private EstadoPedido estado;

	@NotNull
	@CreatedDate
	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "DATAMUDANCA", nullable = false)
	protected LocalDateTime dataMudanca;

}
