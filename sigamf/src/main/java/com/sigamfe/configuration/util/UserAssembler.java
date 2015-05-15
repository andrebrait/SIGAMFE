package com.sigamfe.configuration.util;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigamfe.model.Usuario;
import com.sigamfe.model.enums.IndicadorSN;

@Service("userAssembler")
public class UserAssembler {

	@Transactional(readOnly = true)
	public User buildUserFromUserEntity(Usuario usuario) {
		String username = usuario.getLogin();
		String password = usuario.getSenha();
		boolean enabled = usuario.getAtivo().equals(IndicadorSN.SIM);
		boolean accountNonExpired = enabled;
		boolean credentialsNonExpired = enabled;
		boolean accountNonLocked = enabled;

		Collection<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(usuario.getPermissao().getCodigoSpringSecurity()));

		User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}

}
