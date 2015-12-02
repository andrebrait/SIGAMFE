package com.sigamfe.base;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sigamfe.business.UsuarioBusiness;
import com.sigamfe.configuration.SigamfeContext;
import com.sigamfe.model.Usuario;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.PermissaoUsuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
@Transactional
@SpringBootApplication
@EnableAspectJAutoProxy
public abstract class BaseTest {

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@Autowired
	private PooledPBEStringEncryptor encryptor;

	private static Usuario usuario_static;

	@Before
	public void initialize() {
		SigamfeContext.testing = true;
		if (usuario_static != null) {
			usuarioBusiness.save(usuario_static);
			SigamfeContext.usuarioLogado = usuario_static;
			return;
		}
		Usuario usuario = usuarioBusiness.findByLogin("admin123");
		if (usuario == null) {
			try {
				usuario = new Usuario();
				usuario.setLogin("admin123");
				usuario.setSenhaEncriptando(encryptor, "admin123");
				usuario.setAtivo(IndicadorSN.SIM);
				usuario.setCpf("014.795.246-89");
				usuario.setTelefone(3133333333L);
				usuario.setPermissao(PermissaoUsuario.ADMINISTRADOR);
				usuario_static = usuarioBusiness.save(usuario);
			} catch (Exception e) {

			}
		}
		SigamfeContext.usuarioLogado = usuario_static;
	}

}
