package com.sigamfe.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sigamfe.model.TelefoneCliente.TelefoneClientePK;
import com.sigamfe.model.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "telefonecliente")
@Data
@ToString(callSuper = false, exclude = "cliente")
@EqualsAndHashCode(callSuper = false, of = "id")
public class TelefoneCliente implements BaseEntity<TelefoneClientePK> {

	private static final long serialVersionUID = -6897413676120195334L;

	@Data
	@Embeddable
	@AllArgsConstructor
	@NoArgsConstructor
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

	@Version
	@Column(name = "VERSION")
	private Long version;

	/**
	 * Atribui o cliente a esta entidade
	 *
	 * @param cliente
	 *            o novo cliente.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		this.getId().setIdCliente(cliente == null ? null : cliente.getId());
	}

}
