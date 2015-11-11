package com.sigamfe.model;

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
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Configurable;

import com.sigamfe.model.base.AuditableBaseEntity;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.PermissaoUsuario;
import com.sigamfe.model.enums.converter.IndicadorSNConverter;
import com.sigamfe.model.enums.converter.PermissaoUsuarioConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Classe Usuario. Representa um usuário do sistema.
 */

@Entity
@Table(name = "usuario")
@Data
@ToString(callSuper = true, exclude = { "usuarioCriacao", "usuarioAtualizacao" })
@EqualsAndHashCode(callSuper = false, of = "id")
@AttributeOverrides(value = {
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "DATACRIACAO", nullable = false) ),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "DATAATUALIZACAO", nullable = false) ) })
public class Usuario extends AuditableBaseEntity<Integer> {

	private static final long serialVersionUID = 345500811513095092L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotEmpty
	@Size(min = 6, max = 20)
	@Column(name = "LOGIN", length = 20, nullable = false, unique = true)
	private String login;

	@NotEmpty
	@Column(name = "SENHA", nullable = false, length = 1000)
	private String senha;

	@NotNull
	@Convert(converter = PermissaoUsuarioConverter.class)
	@Column(name = "PERMISSAO", nullable = false, length = 1)
	private PermissaoUsuario permissao;

	@NotEmpty
	@CPF
	@Size(max = 15)
	@Column(name = "CPF", nullable = false, unique = true, length = 15)
	private String cpf;

	@NotNull
	@Digits(integer = 11, fraction = 0)
	@Column(name = "TELEFONE", nullable = false)
	private Long telefone;

	@NotNull
	@Convert(converter = IndicadorSNConverter.class)
	@Column(name = "INDICADORATIVO", nullable = false, length = 1)
	private IndicadorSN ativo;

	@ManyToOne
	@JoinColumn(name = "USUARIOCRIACAO", nullable = true)
	private Usuario usuarioCriacao;

	@ManyToOne
	@JoinColumn(name = "USUARIOATUALIZACAO", nullable = true)
	private Usuario usuarioAtualizacao;

	@Version
	@Column(name = "VERSION")
	private Long version;

	public String getSenhaDecriptada(PooledPBEStringEncryptor encryptor) {
		return encryptor.decrypt(senha);
	}

	public void setSenhaEncriptando(PooledPBEStringEncryptor encryptor, String senha) {
		setSenha(encryptor.encrypt(senha));
	}

}
