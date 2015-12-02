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

	@Before
	public void initialize() {
		if (SigamfeContext.usuarioLogado == null) {
			Usuario usuario = new Usuario();
			usuario.setLogin("testeAdmin");
			usuario.setSenhaEncriptando(encryptor, "testePass");
			usuario.setAtivo(IndicadorSN.SIM);
			usuario.setCpf("015.338.906-09");
			usuario.setTelefone(3133333333L);
			usuario.setPermissao(PermissaoUsuario.ADMINISTRADOR);
			usuarioBusiness.save(usuario);
			SigamfeContext.usuarioLogado = usuario;
		}
	}

}
