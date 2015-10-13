package com.sigamfe.model;

import java.io.Serializable;
import java.math.BigDecimal;

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

import com.sigamfe.model.FornecedorMaterial.FornecedorMaterialPK;
import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.enums.IndicadorUnidade;
import com.sigamfe.model.enums.converter.IndicadorUnidadeConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "fornecedormaterial")
@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false, of = "id")
public class FornecedorMaterial implements BaseEntity<FornecedorMaterialPK> {

	private static final long serialVersionUID = 471866594674106000L;

	@Embeddable
	@Data
	@ToString(callSuper = false)
	@EqualsAndHashCode(callSuper = false, of = { "idFornecedor", "idMaterial" })
	public static class FornecedorMaterialPK implements Serializable {

		private static final long serialVersionUID = 1228615997649469608L;

		@NotNull
		@Digits(fraction = 0, integer = 10)
		@Column(name = "IDFORNECEDOR", nullable = false, precision = 10, scale = 0)
		private Integer idFornecedor;

		@NotNull
		@Digits(fraction = 0, integer = 10)
		@Column(name = "IDMATERIAL", nullable = false, precision = 10, scale = 0)
		private Integer idMaterial;
	}

	@EmbeddedId
	private FornecedorMaterialPK id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "IDFORNECEDOR", nullable = false, insertable = false, updatable = false)
	private Fornecedor fornecedor;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "IDMATERIAL", nullable = false, insertable = false, updatable = false)
	private Material material;

	@NotNull
	@Digits(fraction = 2, integer = 6)
	@Column(name = "PRECO", nullable = false, precision = 8, scale = 2)
	private BigDecimal preco;

	@NotNull
	@Convert(converter = IndicadorUnidadeConverter.class)
	@Column(name = "UNIDADE", nullable = false, length = 2)
	private IndicadorUnidade unidade;

	/**
	 * Atribui o fornecedor e o ID do fornecedor ao ID desta entidade.
	 *
	 * @param fornecedor
	 *            o novo fornecedor
	 */
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
		this.getId().setIdFornecedor(fornecedor.getId());
	}

	/**
	 * Atribui o material e o ID do material ao ID desta entidade.
	 *
	 * @param material
	 *            o novo material
	 */
	public void setMaterial(Material material) {
		this.material = material;
		this.getId().setIdMaterial(material.getId());
	}

}
