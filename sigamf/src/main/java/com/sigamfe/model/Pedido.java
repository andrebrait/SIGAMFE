package com.sigamfe.model;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.enums.EntregaPedido;
import com.sigamfe.model.enums.FormaPagamento;
import com.sigamfe.model.enums.converters.EntregaPedidoConverter;
import com.sigamfe.model.enums.converters.FormaPagamentoConverter;

@Entity
@Table(name = "pedido")
@Data
@ToString(callSuper = true, exclude = { "materiaisPedido", "pendencias" })
@EqualsAndHashCode(callSuper = false, of = "id")
@AttributeOverrides(value = { @AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = true)) })
public class Pedido extends BaseEntity<Integer> {

	private static final long serialVersionUID = 849368848948346967L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotNull
	@Digits(fraction = 0, integer = 6)
	@Column(name = "CODIGO", precision = 6, scale = 0, nullable = false, unique = true)
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
	@Column(name = "DESCONTO", nullable = false, precision = 3, scale = 2)
	private Float desconto;

	@NotNull
	@Convert(converter = EntregaPedidoConverter.class)
	@Column(name = "TURNOENTREGA", nullable = false, length = 1)
	private EntregaPedido turnoEntrega;

	@Size(max = 200)
	@Column(name = "ENDERECOENTREGA", nullable = true, length = 200)
	private String enderecoEntrega;

	@Digits(fraction = 2, integer = 6)
	@Column(name = "TAXAENTREGA", nullable = true, precision = 8, scale = 2)
	private Float taxaEntrega;

	@NotNull
	@Digits(fraction = 2, integer = 6)
	@Column(name = "TOTAL", nullable = false, precision = 8, scale = 2)
	private Float total;

	@Column(name = "DATAENTREGA", nullable = true)
	private Date dataEntrega;

	@NotNull
	@Column(name = "DATADEVOLUCAO", nullable = false)
	private Date dataDevolucao;

	@Size(max = 500)
	@Column(name = "OBSERVACAO", nullable = true, length = 500)
	private String observacao;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
	private List<PedidoMaterial> materiaisPedido;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
	private List<PedidoPendencia> pendencias;
}
