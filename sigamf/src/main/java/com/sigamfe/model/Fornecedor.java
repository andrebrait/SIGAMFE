package com.sigamfe.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sigamfe.model.base.AuditableBaseEntity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe Fornecedor. Representa um fornecedor de materiais.
 */

@Setter(AccessLevel.NONE)
@Entity
@Table(name = "fornecedor")
@Data
@ToString(callSuper = true, exclude = { "fornecedorMateriais", "telefones", "usuarioCriacao", "usuarioAtualizacao" })
@EqualsAndHashCode(callSuper = false, of = "id")
@AttributeOverrides(value = {
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false) ),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = false) ) })
public class Fornecedor extends AuditableBaseEntity<Integer> {

	private static final long serialVersionUID = 5810285113874724335L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotEmpty
	@Size(max = 200)
	@Column(name = "NOME", length = 200, nullable = false)
	private String descricao;

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

	@OneToMany(mappedBy = "fornecedor")
	private List<FornecedorMaterial> fornecedorMateriais;

	@OneToMany(mappedBy = "fornecedor")
	private List<TelefoneFornecedor> telefones;

	@Override
	public void setId(Integer id) {
		onFieldChange("id", id);
		this.id = id;
	}

	public void setDescricao(String descricao) {
		onFieldChange("id", id);
		this.descricao = descricao;
	}

	@Override
	public void setUsuarioCriacao(Usuario usuarioCriacao) {
		onFieldChange("usuarioCriacao", usuarioCriacao);
		this.usuarioCriacao = usuarioCriacao;
	}

	@Override
	public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
		onFieldChange("usuarioAtualizacao", usuarioAtualizacao);
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	@Override
	public void setVersion(Long version) {
		onFieldChange("version", version);
		this.version = version;
	}

	public void setFornecedorMateriais(List<FornecedorMaterial> fornecedorMateriais) {
		onFieldChange("fornecedorMateriais", fornecedorMateriais);
		this.fornecedorMateriais = fornecedorMateriais;
	}

	public void setTelefones(List<TelefoneFornecedor> telefones) {
		onFieldChange("telefones", telefones);
		this.telefones = telefones;
	}

}
