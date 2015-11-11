package com.sigamfe;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.LoadTimeWeavingConfigurer;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;

import com.sigamfe.business.UsuarioBusiness;
import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.controller.LoginController;
import com.sigamfe.model.Usuario;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.PermissaoUsuario;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.stage.Stage;

@Lazy
@SpringBootApplication
@EnableLoadTimeWeaving
@EnableSpringConfigured
public class SigamfeApp extends Application implements LoadTimeWeavingConfigurer {

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	public static ConfigurableApplicationContext applicationContext;

	@Override
	public void init() throws Exception {
		applicationContext = SpringApplication.run(getClass());
	}

	@Override
	public void stop() throws Exception {
		applicationContext.close();
		super.stop();
	}

	@Override
	public void start(Stage stage) throws Exception {

		notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(this);

		if (usuarioBusiness.count() == 0) {
			Usuario usuario = new Usuario();
			usuario.setLogin("admSigamfe");
			usuario.setSenhaEncriptando(applicationContext.getBean(PooledPBEStringEncryptor.class), "sigPass");
			usuario.setAtivo(IndicadorSN.SIM);
			usuario.setCpf("014.795.246-89");
			usuario.setTelefone(930040829L);
			usuario.setPermissao(PermissaoUsuario.ADMINISTRADOR);
			usuarioBusiness.save(usuario);
		}

		new LoginController();

	}

	public static void main(String[] args) {

		PersistenceConfiguration.setDB_HOSTNAME("brait.zapto.org");
		PersistenceConfiguration.setDB_PORT("52000");

		launch(args);
	}

	@Override
	public LoadTimeWeaver getLoadTimeWeaver() {
		return new InstrumentationLoadTimeWeaver();
	}
}
