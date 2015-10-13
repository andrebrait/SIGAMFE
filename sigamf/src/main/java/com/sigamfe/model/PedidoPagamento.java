package com.sigamfe.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.sigamfe.model.PedidoPagamento.PedidoPagamentoPK;
import com.sigamfe.model.base.AuditableBaseEntity;
import com.sigamfe.model.converter.LocalDateTimeConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "pedidopagamento")
@Data
@ToString(callSuper = true, exclude = { "pedido", "usuarioCriacao", "usuarioAtualizacao" })
@EqualsAndHashCode(callSuper = false, of = "id")
@AttributeOverrides(value = {
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false) ),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = false) ) })
public class PedidoPagamento extends AuditableBaseEntity<PedidoPagamentoPK> {

	private static final long serialVersionUID = 7209346864721608568L;

	@Embeddable
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString(callSuper = false)
	@EqualsAndHashCode(callSuper = false, of = { "idPedido", "parcela" })
	public static class PedidoPagamentoPK implements Serializable {

		private static final long serialVersionUID = -739747676661525329L;

		@NotNull
		@Digits(fraction = 0, integer = 10)
		@Column(name = "IDPEDIDO", nullable = false, precision = 10, scale = 0)
		private Integer idPedido;

		@NotNull
		@Digits(fraction = 0, integer = 2)
		@Column(name = "PARCELA", nullable = false, precision = 2, scale = 0)
		private Short parcela;
	}

	@EmbeddedId
	private PedidoPagamentoPK id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "IDPEDIDO", nullable = false, insertable = false, updatable = false)
	private Pedido pedido;

	@NotNull
	@Digits(fraction = 2, integer = 6)
	@Column(name = "VALOR", nullable = false, scale = 2, precision = 8)
	private BigDecimal valor;

	@NotNull
	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "DATAESPERADA", nullable = false)
	private LocalDateTime dataEsperada;

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "DATAPAGAMENTO", nullable = true)
	private LocalDateTime dataPagamento;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOCRIACAO", nullable = false)
	private Usuario usuarioCriacao;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOATUALIZACAO", nullable = false)
	private Usuario usuarioAtualizacao;

	/**
	 * Atribui o pedido a esta entidade.
	 *
	 * @param pedido
	 *            o novo pedido
	 */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
		this.getId().setIdPedido(pedido == null ? null : pedido.getId());
	}
}
