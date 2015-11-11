package com.sigamfe.business;

import com.sigamfe.business.base.BaseBusiness;
import com.sigamfe.model.Usuario;

public interface UsuarioBusiness extends BaseBusiness<Integer, Usuario> {

	boolean login(String username, String password);

	Usuario findByLogin(String login);

}
