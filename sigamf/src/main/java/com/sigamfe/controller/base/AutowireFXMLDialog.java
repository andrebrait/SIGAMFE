package com.sigamfe.controller.base;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import com.sigamfe.controller.iface.DialogController;

public class AutowireFXMLDialog extends Stage {

	@Autowired
	private ApplicationContext context;

	protected final Object controller;

	public AutowireFXMLDialog(URL fxml, Window owner) {
		this(fxml, owner, StageStyle.DECORATED);
	}

	public AutowireFXMLDialog(URL fxml, Window owner, StageStyle style) {
		super(style);
		initOwner(owner);
		initModality(Modality.WINDOW_MODAL);
		final FXMLLoader loader = new FXMLLoader(fxml);
		try {
			setScene(new Scene((Parent) loader.load()));
			controller = loader.getController();
			if (controller instanceof DialogController) {
				((DialogController) controller).setDialog(this);
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	@PostConstruct
	private void postConstruct() {
		context.getAutowireCapableBeanFactory().autowireBeanProperties(controller, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
	}
}