package com.sigamfe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.factory.annotation.Configurable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Classe ClientePJ. Representa um cliente pessoa jurídica.
 */
@Configurable
@Entity
@Table(name = "clientepj")
@Data
@EqualsAndHashCode(callSuper = true, exclude = "cnpj")
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
public class ClientePJ extends Cliente {

	private static final long serialVersionUID = 8965389116825072094L;

	@CNPJ
	@NotBlank
	@Size(max = 16)
	@Column(name = "CNPJ", nullable = false, unique = true, length = 16)
	private String cnpj;

}
