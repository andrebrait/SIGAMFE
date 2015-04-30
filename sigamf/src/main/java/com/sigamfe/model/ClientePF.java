package com.sigamfe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

	@Digits(fraction = 0, integer = 11)
	@Column(name = "CPF", nullable = true, unique = true)
	private Long cpf;

	@Digits(fraction = 0, integer = 11)
	@Column(name = "CNH", nullable = true, unique = true)
	private Long cnh;

	@Size(min = 7, max = 10)
	@Column(name = "RG", nullable = true, unique = true)
	private String rg;

}
