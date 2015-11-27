package com.sigamfe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;

import com.sigamfe.business.UsuarioBusiness;
import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.model.Usuario;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.PermissaoUsuario;

import javafx.stage.Stage;

@Lazy
@SpringBootApplication
@EnableAspectJAutoProxy
public class SigamfeApp extends AbstractJavaFxApplicationSupport {

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@Override
	public void start(Stage primaryStage) throws Exception {
		if (usuarioBusiness.count() == 0) {
			Usuario usuario = new Usuario();
			usuario.setLogin("admin");
			usuario.setSenhaEncriptando(usuarioBusiness.getEncryptor(), "admin");
			usuario.setAtivo(IndicadorSN.SIM);
			usuario.setCpf("123.456.789-10");
			usuario.setTelefone(3133333333L);
			usuario.setPermissao(PermissaoUsuario.ADMINISTRADOR);
			usuarioBusiness.save(usuario);
		}
	}

	public static void main(String[] args) {

		PersistenceConfiguration.setDB_HOSTNAME("brait.zapto.org");
		PersistenceConfiguration.setDB_PORT("52000");

		launchApp(SigamfeApp.class, args);
	}

}
