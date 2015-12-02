package com.sigamfe.business;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;

import com.sigamfe.business.base.BaseBusiness;
import com.sigamfe.model.Usuario;

public interface UsuarioBusiness extends BaseBusiness<Integer, Usuario> {

	boolean login(String username, String password);

	Usuario findByLogin(String login);

	PooledPBEStringEncryptor getEncryptor();

	Usuario findByCpf(String cpf);

}
