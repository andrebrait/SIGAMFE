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
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.sigamfe.model.FornecedorMaterial.FornecedorMaterialPK;
import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.enums.IndicadorUnidade;
import com.sigamfe.model.enums.converter.IndicadorUnidadeConverter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter(AccessLevel.NONE)
@Entity
@Table(name = "fornecedormaterial")
@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false, of = "id")
public class FornecedorMaterial extends BaseEntity<FornecedorMaterialPK> {

	private static final long serialVersionUID = 471866594674106000L;

	@Embeddable
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
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

	@Version
	@Column(name = "VERSION")
	private Long version;

	/**
	 * Atribui o fornecedor e o ID do fornecedor ao ID desta entidade.
	 *
	 * @param fornecedor
	 *            o novo fornecedor
	 */
	public void setFornecedor(Fornecedor fornecedor) {
		onFieldChange("fornecedor", fornecedor);
		this.fornecedor = fornecedor;
		if (this.id == null) {
			this.id = new FornecedorMaterialPK();
		}
		this.getId().setIdFornecedor(fornecedor == null ? null : fornecedor.getId());
	}

	/**
	 * Atribui o material e o ID do material ao ID desta entidade.
	 *
	 * @param material
	 *            o novo material
	 */
	public void setMaterial(Material material) {
		onFieldChange("material", material);
		this.material = material;
		if (this.id == null) {
			this.id = new FornecedorMaterialPK();
		}
		this.getId().setIdMaterial(material == null ? null : material.getId());
	}

	@Override
	public void setId(FornecedorMaterialPK id) {
		onFieldChange("id", id);
		this.id = id;
	}

	public void setPreco(BigDecimal preco) {
		onFieldChange("preco", preco);
		this.preco = preco;
	}

	public void setUnidade(IndicadorUnidade unidade) {
		onFieldChange("unidade", unidade);
		this.unidade = unidade;
	}

	@Override
	public void setVersion(Long version) {
		onFieldChange("version", version);
		this.version = version;
	}

}
