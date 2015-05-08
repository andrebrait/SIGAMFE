package com.sigamfe.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sigamfe.model.Usuario;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

	@Autowired
	private EntityManager em;

	@Override
	public Usuario findTest() {
		return new Usuario();
	}

}
