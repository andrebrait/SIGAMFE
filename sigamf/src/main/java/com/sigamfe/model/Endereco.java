package com.sigamfe.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.sigamfe.model.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "endereco")
@Data
@ToString(callSuper = false, exclude = { "clientes", "pedidos" })
@EqualsAndHashCode(callSuper = false, of = "id")
public class Endereco implements BaseEntity<Long> {

	private static final long serialVersionUID = -5154366229868438606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotBlank
	@Size(max = 200)
	@Column(name = "LOGRADOURO", nullable = false, length = 200)
	private String logradouro;

	@NotNull
	@Digits(integer = 5, fraction = 0)
	@Range(min = 0, max = 99999)
	@Column(name = "NUMERO", nullable = true, precision = 5, scale = 0)
	private Short numero;

	@Size(min = 9, max = 9)
	@Column(name = "CEP", nullable = true, length = 200)
	private String cep;

	@NotBlank
	@Size(max = 200)
	@Column(name = "CIDADE", nullable = false, length = 200)
	private String cidade;

	@NotBlank
	@Size(max = 2)
	@Column(name = "UF", nullable = false, length = 2)
	private String uf;

	@OneToMany(mappedBy = "endereco")
	private List<Cliente> clientes;

	@OneToMany(mappedBy = "enderecoEntrega")
	private List<Pedido> pedidos;

	@Override
	public Long getVersion() {
		return null;
	}

	@Override
	public void setVersion(Long version) {

	}

}
