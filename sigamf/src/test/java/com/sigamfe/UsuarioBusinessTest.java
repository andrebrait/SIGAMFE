package com.sigamfe;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sigamfe.base.BaseTest;
import com.sigamfe.business.UsuarioBusiness;
import com.sigamfe.model.Usuario;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.PermissaoUsuario;

public class UsuarioBusinessTest extends BaseTest {

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@Autowired
	private PooledPBEStringEncryptor encryptor;

	@Test
	public void test_findAll() {
		Usuario usuario = new Usuario();
		usuario.setLogin("admSigamfe");
		usuario.setSenhaEncriptando(encryptor, "sigPass");
		usuario.setAtivo(IndicadorSN.SIM);
		usuario.setCpf("015.338.906-09");
		usuario.setTelefone(930040829L);
		usuario.setPermissao(PermissaoUsuario.ADMINISTRADOR);
		usuarioBusiness.save(usuario);
		Assert.assertTrue(!usuarioBusiness.findAll().isEmpty());
	}

}
