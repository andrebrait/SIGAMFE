package com.sigamfe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe ClientePF. Representa um cliente pessoa física.
 */

@Entity
@Table(name = "clientepf")
@Data
@Setter(AccessLevel.NONE)
@EqualsAndHashCode(callSuper = true, exclude = { "cpf", "rg", "cnh" })
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
public class ClientePF extends Cliente {

	/*
	 * TODO obedecer à RN para que CPF, CNH e RG sejam unicos e que haja pelo
	 * menos um
	 */

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7903983878811741635L;

	/** The cpf. */
	@CPF
	@NotBlank
	@Size(max = 15)
	@Column(name = "CPF", nullable = true, unique = true, length = 15)
	private String cpf;

	/** The cnh. */
	@Size(max = 11)
	@Setter(AccessLevel.NONE)
	@Column(name = "CNH", nullable = true, unique = true, length = 11)
	private String cnh;

	/** The rg. */
	@Size(max = 10)
	@Setter(AccessLevel.NONE)
	@Column(name = "RG", nullable = true, unique = true, length = 10)
	private String rg;

	/**
	 * Sets the cpf.
	 *
	 * @param cpf
	 *            the cpf to set
	 */
	public void setCpf(String cpf) {
		onFieldChange("cpf", cpf);
		this.cpf = cpf;
	}

	/**
	 * Sets the cnh.
	 *
	 * @param cnh
	 *            the cnh to set
	 */
	@Override
	public void setCnh(String cnh) {
		super.setCnh(cnh);
		this.cnh = cnh;
	}

	/**
	 * Sets the rg.
	 *
	 * @param rg
	 *            the rg to set
	 */
	@Override
	public void setRg(String rg) {
		super.setRg(rg);
		this.rg = rg;
	}

}
