package com.sigamfe.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sigamfe.configuration.util.UserAssembler;
import com.sigamfe.model.Usuario;
import com.sigamfe.repository.UsuarioRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UserAssembler assembler;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException, DataAccessException {

		Usuario usuario = usuarioRepository.findByLogin(arg0);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado no sistema.");
		}

		return assembler.buildUserFromUserEntity(usuario);
	}

}
