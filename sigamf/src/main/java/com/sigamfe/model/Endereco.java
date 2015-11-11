package com.sigamfe.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Configurable;

import com.sigamfe.model.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Table(name = "endereco")
@Data
@ToString(callSuper = false, exclude = { "cliente", "pedidos" })
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

	@Size(max = 30)
	@Column(name = "NUMERO", nullable = true, length = 30)
	private String numero;

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

	@OneToOne(mappedBy = "endereco")
	private Cliente cliente;

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
