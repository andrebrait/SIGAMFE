package com.sigamfe.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate4.type.EncryptedStringType;

import com.sigamfe.model.base.BaseEntity;

/**
 * The Class Usuario.
 */

@TypeDef(
		name = "encryptedString",
		typeClass = EncryptedStringType.class,
		parameters = {
				@Parameter(name = "encryptorRegisteredName", value = "hibernateSenhaEncryptor")
		})
@Entity
@Table(name = "usuario")
@Data
@EqualsAndHashCode(callSuper = false, of = "login")
@AttributeOverrides(value = { @AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = true)) })
public class Usuario extends BaseEntity<String> {

	private static final long serialVersionUID = 345500811513095092L;

	@Id
	@Size(min = 6, max = 50)
	@Column(name = "LOGIN", length = 50)
	private String login;

	@NotNull
	@Column(name = "SENHA", nullable = false, length = 256)
	@Type(type = "encryptedString")
	private String senha;

	@Override
	public String getId() {
		return login;
	}

	@Override
	public void setId(String id) {
		login = id;
	}

}
