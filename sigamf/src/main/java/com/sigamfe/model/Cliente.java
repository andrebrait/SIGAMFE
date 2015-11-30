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

import com.sigamfe.model.base.AuditableBaseEntity;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.converter.IndicadorSNConverter;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe cliente. Representa um cliente no sistema, seja PJ ou PF.
 */
@Entity
@Table(name = "cliente")
@Data
@Setter(AccessLevel.NONE)
@ToString(callSuper = true, exclude = { "pedidos", "telefones", "usuarioCriacao", "usuarioAtualizacao" })
@EqualsAndHashCode(callSuper = false, of = "id")
@AttributeOverrides(value = {
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false) ),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = false) ) })
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente extends AuditableBaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	/** The nome. */
	@NotBlank
	@Size(max = 100)
	@Column(name = "NOME", nullable = false, length = 100)
	private String nome;

	/** The endereco. */
	@NotNull
	@OneToOne
	@JoinColumn(name = "ENDERECO", nullable = false)
	private Endereco endereco;

	/** The email. */
	@Email
	@Size(max = 200)
	@Column(name = "EMAIL", nullable = true, length = 200)
	private String email;

	/** The ja foi bloqueado. */
	@NotNull
	@Convert(converter = IndicadorSNConverter.class)
	@Column(name = "INDICADORHISTORICOBLOQUEIO", nullable = false, length = 1)
	private IndicadorSN jaFoiBloqueado;

	/** The bloqueado. */
	@NotNull
	@Convert(converter = IndicadorSNConverter.class)
	@Column(name = "INDICADORBLOQUEIO", nullable = false, length = 1)
	private IndicadorSN bloqueado;

	/** The usuario criacao. */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOCRIACAO", nullable = false)
	private Usuario usuarioCriacao;

	/** The usuario atualizacao. */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOATUALIZACAO", nullable = false)
	private Usuario usuarioAtualizacao;

	/** The version. */
	@Version
	@Column(name = "VERSION")
	private Long version;

	/** The pedidos. */
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	/** The telefones. */
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<TelefoneCliente> telefones;

	/** The cp. */
	@Transient
	private String cp;

	/** The cnh. */
	@Transient
	private String cnh;

	/** The rg. */
	@Transient
	private String rg;

	/**
	 * Seta o CPF se for pessoa física ou CNPJ se for pessoa jurídica.
	 *
	 * @param cp
	 *            the new cp
	 */
	public void setCp(String cp) {
		onFieldChange("cp", cp);
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
	 * @return the cp
	 */
	public String getCp() {
		if (this instanceof ClientePF) {
			return ((ClientePF) this).getCpf();
		} else if (this instanceof ClientePJ) {
			return ((ClientePJ) this).getCnpj();
		} else {
			return this.cp;
		}
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(Integer id) {
		onFieldChange("id", id);
		this.id = id;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		onFieldChange("nome", nome);
		this.nome = nome;
	}

	/**
	 * Sets the endereco.
	 *
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(Endereco endereco) {
		onFieldChange("endereco", endereco);
		this.endereco = endereco;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		onFieldChange("email", email);
		this.email = email;
	}

	/**
	 * Sets the ja foi bloqueado.
	 *
	 * @param jaFoiBloqueado
	 *            the jaFoiBloqueado to set
	 */
	public void setJaFoiBloqueado(IndicadorSN jaFoiBloqueado) {
		onFieldChange("jaFoiBloqueado", jaFoiBloqueado);
		this.jaFoiBloqueado = jaFoiBloqueado;
	}

	/**
	 * Sets the bloqueado.
	 *
	 * @param bloqueado
	 *            the bloqueado to set
	 */
	public void setBloqueado(IndicadorSN bloqueado) {
		onFieldChange("bloqueado", bloqueado);
		this.bloqueado = bloqueado;
	}

	/**
	 * Sets the usuario criacao.
	 *
	 * @param usuarioCriacao
	 *            the usuarioCriacao to set
	 */
	@Override
	public void setUsuarioCriacao(Usuario usuarioCriacao) {
		onFieldChange("usuarioCriacao", usuarioCriacao);
		this.usuarioCriacao = usuarioCriacao;
	}

	/**
	 * Sets the usuario atualizacao.
	 *
	 * @param usuarioAtualizacao
	 *            the usuarioAtualizacao to set
	 */
	@Override
	public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
		onFieldChange("usuarioAtualizacao", usuarioAtualizacao);
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	/**
	 * Sets the version.
	 *
	 * @param version
	 *            the version to set
	 */
	@Override
	public void setVersion(Long version) {
		onFieldChange("version", version);
		this.version = version;
	}

	/**
	 * Sets the pedidos.
	 *
	 * @param pedidos
	 *            the pedidos to set
	 */
	public void setPedidos(List<Pedido> pedidos) {
		onFieldChange("pedidos", pedidos);
		this.pedidos = pedidos;
	}

	/**
	 * Sets the telefones.
	 *
	 * @param telefones
	 *            the telefones to set
	 */
	public void setTelefones(List<TelefoneCliente> telefones) {
		onFieldChange("telefones", telefones);
		this.telefones = telefones;
	}

	/**
	 * Sets the cnh.
	 *
	 * @param cnh
	 *            the cnh to set
	 */
	public void setCnh(String cnh) {
		onFieldChange("cnh", cnh);
		this.cnh = cnh;
	}

	/**
	 * Sets the rg.
	 *
	 * @param rg
	 *            the rg to set
	 */
	public void setRg(String rg) {
		onFieldChange("rg", rg);
		this.rg = rg;
	}

}
