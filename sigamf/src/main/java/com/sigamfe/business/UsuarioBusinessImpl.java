package com.sigamfe.business;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.Usuario;
import com.sigamfe.repository.UsuarioRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UsuarioBusinessImpl extends AbstractBusiness<Integer, Usuario> implements UsuarioBusiness {

	private static final long serialVersionUID = -5963967420211221748L;

	@Getter
	@Autowired
	private PooledPBEStringEncryptor encryptor;

	@Getter
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Override
	public boolean login(String username, String password) {
		try {
			Authentication authToken = new UsernamePasswordAuthenticationToken(username, password);
			authToken = authenticationProvider.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authToken);
			return true;
		} catch (AuthenticationException e) {
			return false;
		}
	}

	@Override
	public Usuario findByLogin(String login) {
		return repository.findByLogin(login);
	}

}
