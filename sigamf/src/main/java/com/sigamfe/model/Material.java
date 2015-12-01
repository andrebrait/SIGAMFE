package com.sigamfe.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sigamfe.model.base.AuditableBaseEntity;
import com.sigamfe.model.enums.IndicadorUnidade;
import com.sigamfe.model.enums.converter.IndicadorUnidadeConverter;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Setter(AccessLevel.NONE)
@Entity
@Table(name = "material")
@Data
@ToString(callSuper = true, exclude = { "pedidosMaterial", "fornecedoresMaterial", "imagem", "movimentosEstoque",
		"usuarioCriacao", "usuarioAtualizacao" })
@EqualsAndHashCode(callSuper = false, of = "id")
@AttributeOverrides(value = {
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false) ),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = false) ) })
public class Material extends AuditableBaseEntity<Integer> {

	private static final long serialVersionUID = 2542442156839971981L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotNull
	@Digits(fraction = 0, integer = 4)
	@Column(name = "CODIGO", precision = 4, nullable = false, unique = true)
	private Integer codigo;

	@Size(max = 200)
	@Column(name = "DESCRICAO", length = 200, nullable = true)
	private String descricao;

	// Alterar size se precisar. estou supondo que o max seria 9.999.999
	@Size(max = 7)
	@Column(name = "QUANTIDADE", length = 7, nullable = true)
	private Integer quantidade;

	@NotNull
	@Digits(fraction = 2, integer = 6)
	@Column(name = "VALORALUG", nullable = false, precision = 8, scale = 2)
	private BigDecimal valorAluguel;

	@NotNull
	@Digits(fraction = 2, integer = 6)
	@Column(name = "VALORREPO", nullable = false, precision = 8, scale = 2)
	private BigDecimal valorReposicao;

	@NotNull
	@Convert(converter = IndicadorUnidadeConverter.class)
	@Column(name = "UNIDADE", nullable = false, length = 2)
	private IndicadorUnidade unidade;

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

	@OneToMany(mappedBy = "material")
	private List<PedidoMaterial> pedidosMaterial;

	@OneToMany(mappedBy = "material")
	private List<FornecedorMaterial> fornecedoresMaterial;

	@OneToMany(mappedBy = "material")
	private List<MovimentoEstoque> movimentosEstoque;

	@OneToOne(mappedBy = "material")
	private ImagemMaterial imagem;

	@Override
	public void setId(Integer id) {
		onFieldChange("id", id);
		this.id = id;
	}

	public void setCodigo(Integer codigo) {
		onFieldChange("codigo", codigo);
		this.codigo = codigo;
	}

	public void setDescricao(String descricao) {
		onFieldChange("descricao", descricao);
		this.descricao = descricao;
	}

	public void setQuantidade(Integer quantidade) {
		onFieldChange("quantidade", quantidade);
		this.quantidade = quantidade;
	}

	public void setValorAluguel(BigDecimal valorAluguel) {
		onFieldChange("valorAluguel", valorAluguel);
		this.valorAluguel = valorAluguel;
	}

	public void setValorReposicao(BigDecimal valorReposicao) {
		onFieldChange("valorReposicao", valorReposicao);
		this.valorReposicao = valorReposicao;
	}

	public void setUnidade(IndicadorUnidade unidade) {
		onFieldChange("unidade", unidade);
		this.unidade = unidade;
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

	public void setPedidosMaterial(List<PedidoMaterial> pedidosMaterial) {
		onFieldChange("pedidosMaterial", pedidosMaterial);
		this.pedidosMaterial = pedidosMaterial;
	}

	public void setFornecedoresMaterial(List<FornecedorMaterial> fornecedoresMaterial) {
		onFieldChange("fornecedoresMaterial", fornecedoresMaterial);
		this.fornecedoresMaterial = fornecedoresMaterial;
	}

	public void setMovimentosEstoque(List<MovimentoEstoque> movimentosEstoque) {
		onFieldChange("movimentosEstoque", movimentosEstoque);
		this.movimentosEstoque = movimentosEstoque;
	}

	public void setImagem(ImagemMaterial imagem) {
		onFieldChange("imagem", imagem);
		this.imagem = imagem;
	}

}
