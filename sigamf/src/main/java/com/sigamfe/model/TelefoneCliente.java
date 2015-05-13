package com.sigamfe.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "telefonecliente")
@Data
@ToString(callSuper = true, exclude = "cliente")
@EqualsAndHashCode(callSuper = false, of = "id")
public class TelefoneCliente implements Serializable {

	private static final long serialVersionUID = -6897413676120195334L;

	@Data
	@Embeddable
	@ToString(callSuper = false)
	@EqualsAndHashCode(callSuper = false, of = { "idCliente", "telefone" })
	public static class TelefoneClientePK implements Serializable {

		private static final long serialVersionUID = -2520894707311269576L;

		@NotNull
		@Digits(fraction = 0, integer = 10)
		@Column(name = "IDCLIENTE", nullable = false, precision = 10, scale = 0)
		private Integer idCliente;

		@NotNull
		@Digits(fraction = 0, integer = 11)
		@Column(name = "TELEFONE", nullable = false, precision = 11, scale = 0)
		private Long telefone;
	}

	@EmbeddedId
	private TelefoneClientePK id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "IDCLIENTE", nullable = false, insertable = false, updatable = false)
	private Cliente cliente;

	@Size(max = 200)
	@Column(name = "OBSERVACOES", length = 200, nullable = true)
	private String observacoes;

}
