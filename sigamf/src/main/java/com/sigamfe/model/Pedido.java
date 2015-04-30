package com.sigamfe.model;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.enums.EntregaPedido;
import com.sigamfe.model.enums.EstadoPedido;
import com.sigamfe.model.enums.FormaPagamento;
import com.sigamfe.model.enums.converters.FormaPagamentoConverter;

@Entity
@Table(name = "pedido")
@Data
@ToString(callSuper = true, exclude = "materiaisPedido")
@EqualsAndHashCode(callSuper = false, of = "codigo")
@AttributeOverrides(value = {
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = true)) })
public class Pedido extends BaseEntity {

	private static final long serialVersionUID = 849368848948346967L;

	@Id
	@Digits(fraction = 0, integer = 6)
	@Column(name = "CODIGO")
	private Integer codigo;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "CLIENTE", nullable = false)
	private Cliente cliente;

	@NotNull
	@Convert(converter = FormaPagamentoConverter.class)
	@Column(name = "FORMAPAGAMENTO", nullable = false, length = 2)
	private FormaPagamento formaPagamento;

	@NotNull
	@Digits(fraction = 2, integer = 1)
	@Column(name = "DECIMAL", nullable = false)
	private Float desconto;

	private EntregaPedido turnoEntrega;

	private String enderecoEntrega;

	private Long taxaEntrega;

	private Long total;

	private Date dataEntrega;

	private Date dataDevolucao;

	private EstadoPedido estado;

	private String observação;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
	private List<PedidoMaterial> materiaisPedido;
}
