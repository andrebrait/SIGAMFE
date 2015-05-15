package com.sigamfe.controller.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ResourceUtils;

import com.sigamfe.controller.MainWindowController;

public class FXMLDialog extends Stage {

	public static String VIEW_PATH = "classpath:com/sigamfe/views/";

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	private BaseController controller;

	public FXMLDialog(BaseController controller) {
		this.controller = controller;
	}

	private URL getPathByController(Class<? extends BaseController> controller) {
		try {
			return ResourceUtils.getURL(VIEW_PATH + StringUtils.removeEndIgnoreCase(controller.getSimpleName(), "ControllerImpl") + ".fxml");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void initializeWindow() {
		initializeWindow(applicationContext.getBean(MainWindowController.class).getDialog(), null, null);
	}

	public void initializeWindow(Window owner, Modality modality, StageStyle style) {
		Optional.ofNullable(style).ifPresent(s -> initStyle(s));
		Optional.ofNullable(owner).ifPresent(o -> initOwner(o));
		initModality(Optional.ofNullable(modality).orElse(Modality.WINDOW_MODAL));
		FXMLLoader loader = new FXMLLoader(getPathByController(controller.getClass()));
		loader.setControllerFactory(callBack -> controller);
		try {
			setScene(new Scene((Parent) loader.load()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
