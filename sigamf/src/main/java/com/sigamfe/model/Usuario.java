package com.sigamfe.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sigamfe.model.base.BaseEntity;

/**
 * The Class Usuario.
 */
@Entity
@Table(name = "usuario")
@Data
@EqualsAndHashCode(callSuper = false, of = "login")
public class Usuario extends BaseEntity<String> {

	private static final long serialVersionUID = 345500811513095092L;

	@Id
	@Size(min = 6, max = 50)
	@Column(name = "LOGIN", length = 50)
	private String login;

	@NotNull
	@Size(min = 6, max = 15)
	@Column(name = "SENHA", nullable = false, length = 15)
	private String senha;

	@NotNull
	@Column(name = "DATACRIACAO", nullable = false)
	private Date dataCriacao;

	@Column(name = "DATAATUALIZACAO", nullable = true)
	private Date dataAtualizacao;

	@Override
	public String getId() {
		return login;

	}

	@Override
	public void setId(String id) {
		login = id;
	}

}
