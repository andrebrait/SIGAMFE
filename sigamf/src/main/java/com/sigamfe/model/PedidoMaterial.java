package com.sigamfe.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.sigamfe.model.PedidoMaterial.PedidoMaterialPK;
import com.sigamfe.model.base.AbstractBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "pedidomaterial")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, of = "id")
@AttributeOverrides(value = {
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false) ),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = false) ) })
public class PedidoMaterial extends AbstractBaseEntity<PedidoMaterialPK> {

	private static final long serialVersionUID = -2163115726384201247L;

	@Embeddable
	@Data
	@ToString(callSuper = false)
	@EqualsAndHashCode(callSuper = false, of = { "codPedido", "codMaterial" })
	public static class PedidoMaterialPK implements Serializable {

		private static final long serialVersionUID = 1228615997649469608L;

		@NotNull
		@Digits(fraction = 0, integer = 6)
		@Column(name = "IDPEDIDO", nullable = false, precision = 6, scale = 0)
		private Integer codPedido;

		@NotNull
		@Digits(fraction = 0, integer = 4)
		@Column(name = "IDMATERIAL", nullable = false, precision = 4, scale = 0)
		private Integer codMaterial;
	}

	@EmbeddedId
	private PedidoMaterialPK id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "IDPEDIDO", nullable = false, insertable = false, updatable = false)
	private Pedido pedido;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "IDMATERIAL", nullable = false, insertable = false, updatable = false)
	private Material material;

	@NotNull
	@Digits(fraction = 0, integer = 6)
	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantidade;

	@Digits(fraction = 0, integer = 6)
	@Column(name = "QUANTIDADEREPOSICAO", nullable = true)
	private Integer quantidadeReposicao;

	@Digits(fraction = 0, integer = 6)
	@Column(name = "QUANTIDADEDEVOLVIDA", nullable = true)
	private Integer quantidadeDevolvida;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOCRIACAO", nullable = false)
	private Usuario usuarioCriacao;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOATUALIZACAO", nullable = false)
	private Usuario usuarioAtualizacao;
}
