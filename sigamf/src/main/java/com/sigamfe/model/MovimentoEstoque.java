package com.sigamfe.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;

import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.converter.LocalDateTimeConverter;
import com.sigamfe.model.enums.TipoMovimentoEstoque;
import com.sigamfe.model.enums.converter.TipoMovimentoEstoqueConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "movimentoestoque")
@Data
@ToString(callSuper = false, exclude = { "material", "usuario" })
@EqualsAndHashCode(callSuper = false, of = "id")
public class MovimentoEstoque implements BaseEntity<Long> {

	private static final long serialVersionUID = -7364646706571702250L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "IDMATERIAL", nullable = false)
	private Material material;

	@NotNull
	@Digits(integer = 9, fraction = 0)
	@Range(min = -8388608, max = 8388607)
	@Column(name = "QUANTIDADE", nullable = false, scale = 0, precision = 9)
	private Integer quantidade;

	@NotNull
	@Convert(converter = TipoMovimentoEstoqueConverter.class)
	@Column(name = "TIPOMOVIMENTO", nullable = false, length = 2)
	private TipoMovimentoEstoque tipo;

	@NotNull
	@CreatedDate
	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "DATA", nullable = false)
	private LocalDateTime data;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIO", nullable = false)
	private Usuario usuario;

}
