package com.sigamfe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "clientepj")
@Data
@EqualsAndHashCode(callSuper = false, of = "cnpj")
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
public class ClientePJ extends Cliente {

	private static final long serialVersionUID = 8965389116825072094L;

	@NotNull
	@Digits(fraction = 0, integer = 12)
	@Column(name = "CNPJ", nullable = false, unique = true)
	private Long cnpj;

	@Size(max = 50)
	@Column(name = "NOMECONTATO", nullable = true)
	private String nomeContato;

	@Digits(fraction = 0, integer = 11)
	@Column(name = "TELEFONECONTATO", nullable = true)
	private Long telefoneContato;

}
