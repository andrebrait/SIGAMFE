package com.sigamfe.model;

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

import org.springframework.data.annotation.CreatedDate;

import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.converter.LocalDateTimeConverter;
import com.sigamfe.model.enums.EstadoPedido;
import com.sigamfe.model.enums.converter.EstadoPedidoConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "pedidoestado")
@Data
@ToString(callSuper = false, exclude = { "pedido", "usuarioMudanca" })
@EqualsAndHashCode(callSuper = false, of = "id")
public class PedidoEstado implements BaseEntity<Integer> {

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
	private LocalDateTime dataMudanca;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOMUDANCA", nullable = false)
	private Usuario usuarioMudanca;

	@Override
	public Long getVersion() {
		return null;
	}

	@Override
	public void setVersion(Long version) {

	}

}
