package com.sigamfe.views.classes;

import org.springframework.stereotype.Component;

import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.views.classes.base.AbstractFxmlView;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Component
public class MainWindowView extends AbstractFxmlView {

	private static final long serialVersionUID = 2222537671300267045L;

	@Getter
	@Setter
	private Stage currentStage;

	@Override
	public void newStage() {
		Stage stage = new Stage();
		stage.initModality(Modality.NONE);
		stage.setMaximized(true);
		stage.setOnCloseRequest(e -> Platform.exit());
		stage.setTitle(Titles.WINDOW_MAIN);
		stage.setScene(new Scene(getView()));
		setCurrentStage(stage);
		getController().initializeWindow();
	}

}
