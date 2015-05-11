package com.sigamfe.configuration;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.sigamfe.controller.MainWindowController;

@Configuration
@Lazy
public class ScreensConfiguration {

	@Setter
	private Stage primaryStage;

	public void showScreen(Parent screen) {
		primaryStage.setScene(new Scene(screen, 800, 600));
		primaryStage.show();
	}

	@Bean
	public MainWindowController mainWindowController() {
		return new MainWindowController();
	}

}
