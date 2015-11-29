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
import com.sigamfe.views.classes.LoginView;

import javafx.application.Preloader;
import javafx.stage.Stage;

@Lazy
@SpringBootApplication
@EnableAspectJAutoProxy
public class SigamfeApp extends AbstractJavaFxApplicationSupport {

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@Autowired
	private LoginView loginView;

	@Override
	public void start(Stage primaryStage) throws Exception {

		if (usuarioBusiness.count() == 0) {
			Usuario usuario = new Usuario();
			usuario.setLogin("admin123");
			usuario.setSenhaEncriptando(usuarioBusiness.getEncryptor(), "admin123");
			usuario.setAtivo(IndicadorSN.SIM);
			usuario.setCpf("014.795.246-89");
			usuario.setTelefone(3133333333L);
			usuario.setPermissao(PermissaoUsuario.ADMINISTRADOR);
			usuarioBusiness.save(usuario);
		}

		notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));

		loginView.newStage();
		loginView.getCurrentStage().showAndWait();

	}

	public static void main(String[] args) {

		PersistenceConfiguration.setDB_HOSTNAME("brait.zapto.org");
		PersistenceConfiguration.setDB_PORT("52000");

		launchApp(SigamfeApp.class, args);
	}

}
