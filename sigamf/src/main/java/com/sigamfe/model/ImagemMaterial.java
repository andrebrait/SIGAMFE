package com.sigamfe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.sigamfe.model.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "material")
@Data
@ToString(callSuper = false, exclude = "imagem")
@EqualsAndHashCode(callSuper = false, of = "id")
public class ImagemMaterial implements BaseEntity<Integer> {

	private static final long serialVersionUID = -2843596916310581046L;

	@Id
	@Column(name = "IDMATERIAL")
	private Integer id;

	@NotNull
	@OneToOne
	@JoinColumn(name = "IDMATERIAL", nullable = false, insertable = false, updatable = false)
	private Material material;

	@NotEmpty
	@Column(name = "IMAGEM", nullable = false)
	private byte[] imagem;

	/**
	 * Atribui o material a esta entidade.
	 *
	 * @param material
	 *            O novo material.
	 */
	public void setMaterial(Material material) {
		this.material = material;
		this.id = material.getId();
	}

}
