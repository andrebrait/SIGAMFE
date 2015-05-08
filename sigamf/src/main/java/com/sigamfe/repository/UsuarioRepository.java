package com.sigamfe.repository;

import org.springframework.stereotype.Component;

import com.sigamfe.model.Usuario;
import com.sigamfe.repository.base.BaseRepository;

@Component
public interface UsuarioRepository extends BaseRepository<Usuario, Integer>, UsuarioRepositoryCustom {

	public Usuario findByLogin(String login);

}
