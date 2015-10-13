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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

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
@ToString(callSuper = true, exclude = { "pedidos", "telefones" })
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

	@NotEmpty
	@Size(max = 50)
	@Column(name = "NOME", nullable = false, length = 50)
	private String nome;

	@NotNull
	@Size(max = 200)
	@Column(name = "ENDERECO", nullable = false, length = 200)
	private String endereco;

	@NotEmpty
	@Size(max = 200)
	@Column(name = "ENDERECOENTREGA", nullable = false, length = 200)
	private String enderecoEntrega;

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

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<TelefoneCliente> telefones;

}
