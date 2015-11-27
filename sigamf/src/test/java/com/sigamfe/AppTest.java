package com.sigamfe;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sigamfe.business.UsuarioBusiness;
import com.sigamfe.model.Usuario;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.PermissaoUsuario;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
@Transactional
@SpringBootApplication
@EnableAspectJAutoProxy
public class AppTest {

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
