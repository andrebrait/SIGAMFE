package com.sigamfe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "clientepj")
@Data
@EqualsAndHashCode(callSuper = false, of = "cnpj")
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
public class ClientePJ extends Cliente {

	private static final long serialVersionUID = 8965389116825072094L;

	@NotNull
	@CNPJ
	@Size(max = 16)
	@Column(name = "CNPJ", nullable = false, unique = true, length = 16)
	private String cnpj;

}
