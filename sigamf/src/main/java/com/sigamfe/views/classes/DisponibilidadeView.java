package com.sigamfe.views.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sigamfe.configuration.constants.Titles;

import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Component
public class DisponibilidadeView {

	@Getter
	@Setter
	private Stage currentStage;

	@Autowired
	private MainWindowView mainWindowView;

	public void newStage() {
		Stage stage = new Stage();
		stage.setScene(new Scene(getView()));
		stage.initOwner(mainWindowView.getCurrentStage());
		stage.setTitle(Titles.WINDOW_CADASTRO);
		stage.setResizable(false);
		setCurrentStage(stage);
		getController().initializeWindow();
	}
}
