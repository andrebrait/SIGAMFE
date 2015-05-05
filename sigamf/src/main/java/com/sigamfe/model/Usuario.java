package com.sigamfe.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate4.type.EncryptedStringType;

import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.PermissaoUsuario;
import com.sigamfe.model.enums.converters.IndicadorSNConverter;
import com.sigamfe.model.enums.converters.PermissaoUsuarioConverter;

/**
 * The Class Usuario.
 */

@TypeDef(
		name = "encryptedString",
		typeClass = EncryptedStringType.class,
		parameters = {
				@Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor")
		})
@Entity
@Table(name = "usuario")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, of = "id")
@AttributeOverrides(value = { @AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = true)) })
public class Usuario extends BaseEntity<Integer> {

	private static final long serialVersionUID = 345500811513095092L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotNull
	@Size(min = 6, max = 50)
	@Column(name = "LOGIN", length = 50, nullable = false, unique = true)
	private String login;

	@NotNull
	@Size(min = 6, max = 15)
	@Column(name = "SENHA", nullable = false, length = 1000)
	@Type(type = "encryptedString")
	private String senha;

	@NotNull
	@Convert(converter = PermissaoUsuarioConverter.class)
	@Column(name = "PERMISSAO", nullable = false, length = 1)
	private PermissaoUsuario permissao;

	@NotNull
	@Digits(integer = 11, fraction = 0)
	@Column(name = "CPF", nullable = false, unique = true)
	private Long cpf;

	@NotNull
	@Digits(integer = 11, fraction = 0)
	@Column(name = "TELEFONE", nullable = false)
	private Long telefone;

	@NotNull
	@Convert(converter = IndicadorSNConverter.class)
	@Column(name = "INDICADORATIVO", nullable = false, length = 1)
	private IndicadorSN ativo;

}
