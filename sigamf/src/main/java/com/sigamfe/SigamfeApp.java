package com.sigamfe;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.stage.Stage;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;

import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.configuration.ScreensConfiguration;
import com.sigamfe.model.Usuario;
import com.sigamfe.repository.UsuarioRepository;

@Lazy
@SpringBootApplication
public class SigamfeApp extends Application {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PooledPBEStringEncryptor pooledPBEStringEncryptor;

	private ConfigurableApplicationContext applicationContext;

	private static String[] savedArgs;

	@Override
	public void init() throws Exception {
		applicationContext = SpringApplication.run(SigamfeApp.class, savedArgs);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		applicationContext.close();
	}

	protected static void launchApp(String[] args) {
		SigamfeApp.savedArgs = args;
		Application.launch(SigamfeApp.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));

		stage.setTitle("SIGAMFE");
		stage.centerOnScreen();
		stage.show();

		final ScreensConfiguration screens = applicationContext.getBean(ScreensConfiguration.class);

		screens.setPrimaryStage(stage);
		screens.loginDialog().show();

		final Usuario usuario = usuarioRepository.findByLogin("admSigamfe");
		System.out.println(usuario);
		System.out.println(usuario.getSenhaDecriptada(pooledPBEStringEncryptor));
	}

	public static void main(String[] args) {

		PersistenceConfiguration.setDB_HOSTNAME("localhost");
		PersistenceConfiguration.setDB_PORT("52000");

		launchApp(args);
	}
}
