package com.sigamfe.configuration.util;

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

import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import com.sigamfe.controller.base.DialogController;

public class FXMLDialog extends Stage {

	public static String VIEW_PATH = "classpath:com/sigamfe/views/";

	public static URL getPathByController(DialogController controller) {
		try {
			return ResourceUtils.getURL(VIEW_PATH + StringUtils.removeEndIgnoreCase(controller.getClass().getSimpleName(), "ControllerImpl")
					+ ".fxml");
		} catch (FileNotFoundException e) {
			throw new RuntimeErrorException(new Error(e));
		}
	}

	public FXMLDialog(DialogController controller) {
		this(controller, null);
	}

	public FXMLDialog(DialogController controller, Window owner) {
		this(controller, owner, null);
	}

	public FXMLDialog(DialogController controller, Window owner, Modality modality) {
		this(controller, owner, modality, null);
	}

	public FXMLDialog(DialogController controller, Window owner, Modality modality, StageStyle style) {
		Optional.ofNullable(style).ifPresent(s -> initStyle(s));
		Optional.ofNullable(owner).ifPresent(o -> initOwner(o));
		initModality(Optional.ofNullable(modality).orElse(Modality.WINDOW_MODAL));
		FXMLLoader loader = new FXMLLoader(getPathByController(controller));
		loader.setControllerFactory(callBack -> controller);
		try {
			setScene(new Scene((Parent) loader.load()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}