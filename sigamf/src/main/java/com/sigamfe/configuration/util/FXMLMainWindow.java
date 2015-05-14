package com.sigamfe.configuration.util;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import com.sigamfe.controller.MainWindowController;

public class FXMLMainWindow extends Stage {

	public FXMLMainWindow(final MainWindowController controller, URL fxml) {
		super(StageStyle.DECORATED);
		initModality(Modality.NONE);
		final FXMLLoader loader = new FXMLLoader(fxml);
		try {
			loader.setControllerFactory(new Callback<Class<?>, Object>() {
				@Override
				public Object call(Class<?> aClass) {
					return controller;
				}
			});
			controller.setDialog(this);
			setScene(new Scene((Parent) loader.load()));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

}
