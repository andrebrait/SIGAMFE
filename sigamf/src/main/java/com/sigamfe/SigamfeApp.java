package com.sigamfe;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.configuration.ScreensConfiguration;
import com.sigamfe.repository.UsuarioRepository;

@SpringBootApplication
public class SigamfeApp extends Application {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void start(Stage primaryStage) {

		final ApplicationContext context = new AnnotationConfigApplicationContext(SigamfeApp.class);
		final ScreensConfiguration screens = context.getBean(ScreensConfiguration.class);
		screens.setPrimaryStage(primaryStage);
		System.out.println(usuarioRepository);
		// screens.loginDialog().show();
	}

	public static void main(String[] args) {

		PersistenceConfiguration.setDB_HOSTNAME("localhost");
		PersistenceConfiguration.setDB_PORT("52000");

		launch(args);
	}
}
