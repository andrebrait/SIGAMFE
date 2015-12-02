package com.sigamfe;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.sigamfe.base.BaseTest;
import com.sigamfe.business.UsuarioBusiness;
import com.sigamfe.model.Usuario;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.PermissaoUsuario;

public class UsuarioBusinessTest extends BaseTest {

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void test_findAll() {
		Usuario usuario = new Usuario();
		usuario.setLogin("admSigamfe");
		usuario.setSenhaEncriptando(applicationContext.getBean(PooledPBEStringEncryptor.class), "sigPass");
		usuario.setAtivo(IndicadorSN.SIM);
		usuario.setCpf("014.795.246-89");
		usuario.setTelefone(930040829L);
		usuario.setPermissao(PermissaoUsuario.ADMINISTRADOR);
		usuarioBusiness.save(usuario);
		Assert.assertTrue(!usuarioBusiness.findAll().isEmpty());
	}

}
