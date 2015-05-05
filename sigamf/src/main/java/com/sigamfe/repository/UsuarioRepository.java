package com.sigamfe.repository;

import com.sigamfe.model.Usuario;
import com.sigamfe.repository.base.BaseRepository;

public interface UsuarioRepository extends UsuarioRepositoryCustom, BaseRepository<Usuario, Integer> {

	public Usuario findByLogin(String login);

}
