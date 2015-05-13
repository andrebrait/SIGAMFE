package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.Usuario;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Integer>, UsuarioRepositoryCustom {

	public Usuario findByLogin(String login);

}
