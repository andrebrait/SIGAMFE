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
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;

import com.sigamfe.model.PedidoMaterial.PedidoMaterialPK;
import com.sigamfe.model.base.AuditableBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "pedidomaterial")
@Data
@ToString(callSuper = true, exclude = { "pedido", "material", "usuarioCriacao", "usuarioAtualizacao" })
@EqualsAndHashCode(callSuper = false, of = "id")
@AttributeOverrides(value = {
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false) ),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = false) ) })
public class PedidoMaterial extends AuditableBaseEntity<PedidoMaterialPK> {

	private static final long serialVersionUID = -2163115726384201247L;

	@Embeddable
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString(callSuper = false)
	@EqualsAndHashCode(callSuper = false, of = { "idPedido", "idMaterial" })
	public static class PedidoMaterialPK implements Serializable {

		private static final long serialVersionUID = 1228615997649469608L;

		@NotNull
		@Digits(fraction = 0, integer = 10)
		@Column(name = "IDPEDIDO", nullable = false, precision = 10, scale = 0)
		private Integer idPedido;

		@NotNull
		@Digits(fraction = 0, integer = 10)
		@Column(name = "IDMATERIAL", nullable = false, precision = 10, scale = 0)
		private Integer idMaterial;
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

	@Version
	@Column(name = "VERSION")
	private Long version;

	/**
	 * Atribui o pedido a esta entidade.
	 *
	 * @param pedido
	 *            o novo pedido
	 */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
		if (this.id == null) {
			this.id = new PedidoMaterialPK();
		}
		this.getId().setIdPedido(pedido == null ? null : pedido.getId());
	}

	/**
	 * Atribui o material a esta entidade.
	 *
	 * @param material
	 *            o novo material
	 */
	public void setMaterial(Material material) {
		this.material = material;
		if (this.id == null) {
			this.id = new PedidoMaterialPK();
		}
		this.getId().setIdMaterial(material == null ? null : material.getId());
	}

}
