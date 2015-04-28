package com.sigamfe.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.enums.IndicadorUnidade;
import com.sigamfe.model.enums.IndicadorUnidade.IndicadorUnidadeConverter;

@Entity
@Table(name = "material")
@Data
@EqualsAndHashCode(callSuper = false, of = "codigo")
public class Material extends BaseEntity<Integer> {

	private static final long serialVersionUID = 2542442156839971981L;

	@Id
	@Column(name = "CODIGO", precision = 4)
	private Integer codigo;

	@Size(max = 100)
	@Column(name = "DESCRICAO", length = 100, nullable = true)
	private String descricao;

	@NotNull
	@Column(name = "VALORALUG", nullable = false, precision = 8, scale = 2)
	private Double valorAluguel;

	@NotNull
	@Column(name = "VALORREPO", nullable = false, precision = 8, scale = 2)
	private Double valorReposicao;

	@NotNull
	@Size(min = 2, max = 2)
	@Convert(converter = IndicadorUnidadeConverter.class)
	@Column(name = "UNIDADE", nullable = false, length = 2)
	private IndicadorUnidade indicadorUnidade;

	@NotNull
	@Column(name = "DATACRIACAO", nullable = false)
	private Date dataCriacao;

	@Column(name = "DATAATUALIZACAO", nullable = true)
	private Date dataAtualizacao;

	@Override
	public Integer getId() {
		return codigo;
	}

	@Override
	public void setId(Integer id) {
		this.codigo = id;
	}

}
