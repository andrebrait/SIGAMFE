package com.sigamfe.views.classes;

import org.springframework.stereotype.Component;

import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.views.classes.base.AbstractFxmlView;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Component
public class LoginView extends AbstractFxmlView {

	private static final long serialVersionUID = -5833035821838307808L;

	@Getter
	@Setter
	private Stage currentStage;

	@Override
	public void newStage() {
		Stage stage = new Stage();
		stage.setScene(new Scene(getView()));
		stage.setOnCloseRequest(e -> Platform.exit());
		stage.setResizable(false);
		stage.setTitle(Titles.WINDOW_LOGIN);
		setCurrentStage(stage);
		getController().initializeWindow();
	}

}
