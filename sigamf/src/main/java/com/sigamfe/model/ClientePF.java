package com.sigamfe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Configurable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Classe ClientePF. Representa um cliente pessoa física.
 */
@Configurable
@Entity
@Table(name = "clientepf")
@Data
@EqualsAndHashCode(callSuper = true, exclude = { "cpf", "rg", "cnh" })
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
public class ClientePF extends Cliente {

	/*
	 * TODO obedecer à RN para que CPF, CNH e RG sejam unicos e que haja pelo
	 * menos um
	 */

	private static final long serialVersionUID = -7903983878811741635L;

	@CPF
	@NotBlank
	@Size(max = 15)
	@Column(name = "CPF", nullable = true, unique = true, length = 15)
	private String cpf;

	@Size(max = 11)
	@Column(name = "CNH", nullable = true, unique = true, length = 11)
	private String cnh;

	@Size(max = 10)
	@Column(name = "RG", nullable = true, unique = true, length = 10)
	private String rg;

}
