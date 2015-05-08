package com.sigamfe;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sigamfe.configuration.ApplicationConfiguration;
import com.sigamfe.configuration.ScreensConfiguration;

public class SigApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		final ScreensConfiguration screens = context.getBean(ScreensConfiguration.class);
		screens.setPrimaryStage(primaryStage);
		screens.loginDialog().show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
