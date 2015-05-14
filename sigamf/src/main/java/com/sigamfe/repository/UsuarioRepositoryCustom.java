package com.sigamfe.repository;

import java.util.List;

import com.sigamfe.model.Usuario;

public interface UsuarioRepositoryCustom {

	/**
	 * Find test.
	 *
	 * @return the usuario
	 */
	public List<Usuario> findAllAtivos();

}
