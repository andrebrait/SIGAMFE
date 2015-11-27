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

import org.springframework.beans.factory.annotation.Configurable;

import com.sigamfe.model.base.AuditableBaseEntity;
import com.sigamfe.model.enums.IndicadorUnidade;
import com.sigamfe.model.enums.converter.IndicadorUnidadeConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Configurable
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

}
