package com.sigamfe.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Configurable;

import com.sigamfe.model.base.AuditableBaseEntity;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.converter.IndicadorSNConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Classe cliente. Representa um cliente no sistema, seja PJ ou PF.
 */

@Entity
@Table(name = "cliente")
@Data
@ToString(callSuper = true, exclude = { "pedidos", "telefones", "usuarioCriacao", "usuarioAtualizacao" })
@EqualsAndHashCode(callSuper = false, of = "id")
@AttributeOverrides(value = {
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false) ),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = false) ) })
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente extends AuditableBaseEntity<Integer> {

	private static final long serialVersionUID = -3608712352669272090L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotBlank
	@Size(max = 100)
	@Column(name = "NOME", nullable = false, length = 100)
	private String nome;

	@NotNull
	@OneToOne
	@JoinColumn(name = "ENDERECO", nullable = false)
	private Endereco endereco;

	@NotBlank
	@Email
	@Size(max = 200)
	@Column(name = "EMAIL", nullable = true, length = 200)
	private String email;

	@NotNull
	@Convert(converter = IndicadorSNConverter.class)
	@Column(name = "INDICADORHISTORICOBLOQUEIO", nullable = false, length = 1)
	private IndicadorSN jaFoiBloqueado;

	@NotNull
	@Convert(converter = IndicadorSNConverter.class)
	@Column(name = "INDICADORBLOQUEIO", nullable = false, length = 1)
	private IndicadorSN bloqueado;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOCRIACAO", nullable = false)
	private Usuario usuarioCriacao;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOATUALIZACAO", nullable = false)
	private Usuario usuarioAtualizacao;

	@Version
	@Column(name = "VERSION")
	private Long version;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<TelefoneCliente> telefones;

	@Transient
	private String cp;

	@Transient
	private Long cnh;

	@Transient
	private String rg;

	/**
	 * Seta o CPF se for pessoa física ou CNPJ se for pessoa jurídica.
	 *
	 * @param cp
	 *            the new cp
	 */
	public void setCp(String cp) {
		if (this instanceof ClientePF) {
			((ClientePF) this).setCpf(cp);
		} else if (this instanceof ClientePJ) {
			((ClientePJ) this).setCnpj(cp);
		} else {
			this.cp = cp;
		}
	}

	/**
	 * Retorna o CPF se for pessoa física ou CNPJ se for jurídica.
	 *
	 * @param cp
	 *            the cp
	 * @return the cp
	 */
	public String getCp(String cp) {
		if (this instanceof ClientePF) {
			return ((ClientePF) this).getCpf();
		} else if (this instanceof ClientePJ) {
			return ((ClientePJ) this).getCnpj();
		} else {
			return this.cp;
		}
	}

}
