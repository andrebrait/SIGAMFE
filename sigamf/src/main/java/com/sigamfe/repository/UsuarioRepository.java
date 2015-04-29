package com.sigamfe.repository;

import org.springframework.stereotype.Component;

import com.sigamfe.model.Usuario;

@Component
public interface UsuarioRepository {

	public Usuario findByLogin(String login);
}
