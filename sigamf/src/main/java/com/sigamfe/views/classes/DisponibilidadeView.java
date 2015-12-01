package com.sigamfe.views.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.views.classes.base.AbstractFxmlView;

import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Component
public class DisponibilidadeView extends AbstractFxmlView {

	private static final long serialVersionUID = -6740906502723241139L;

	@Getter
	@Setter
	private Stage currentStage;

	@Autowired
	private MainWindowView mainWindowView;

	@Override
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
