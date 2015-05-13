package com.sigamfe.configuration;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.sigamfe.configuration.util.FXMLDialog;
import com.sigamfe.controller.LoginController;
import com.sigamfe.controller.system.ErrorController;

@Configuration
@Lazy
public class ScreensConfiguration {

	public static final String viewPath = "/com/sigamfe/view/";

	@Getter
	@Setter
	private Stage primaryStage;

	public void showScreen(Parent screen) {
		showScreen(screen, 800, 600);
	}

	public void showScreen(Parent screen, Integer sizeH, Integer sizeV) {
		primaryStage.setScene(new Scene(screen, sizeH, sizeV));
		primaryStage.show();
	}

	@Bean
	@Scope("prototype")
	public FXMLDialog errorDialog() {
		return new FXMLDialog(errorController(), getClass().getResource(viewPath + "error.fxml"), primaryStage, StageStyle.DECORATED);
	}

	@Bean
	@Scope("prototype")
	public ErrorController errorController() {
		return new ErrorController();
	}

	@Bean
	@Scope("prototype")
	public FXMLDialog loginDialog() {
		return new FXMLDialog(loginController(), getClass().getResource(viewPath + "login.fxml"), primaryStage, StageStyle.DECORATED);
	}

	@Bean
	@Scope("prototype")
	public LoginController loginController() {
		return new LoginController(this);
	}

}
