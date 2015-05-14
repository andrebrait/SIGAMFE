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
import javafx.util.Callback;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import com.sigamfe.controller.iface.DialogController;

public class FXMLDialog extends Stage {

	public static final String VIEW_PATH = "classpath:com/sigamfe/views/";

	public static final URL getPathByController(DialogController controller) {
		try {
			return ResourceUtils.getURL(VIEW_PATH + StringUtils.removeEndIgnoreCase(controller.getClass().getSimpleName(), "Controller") + ".fxml");
		} catch (final FileNotFoundException e) {
			throw new RuntimeErrorException(new Error(e));
		}
	}

	public FXMLDialog(DialogController controller, Window owner) {
		this(controller, owner, Modality.WINDOW_MODAL, StageStyle.DECORATED);
	}

	public FXMLDialog(DialogController controller, Modality modality, Window owner) {
		this(controller, owner, modality, StageStyle.DECORATED);
	}

	public FXMLDialog(DialogController controller, Window owner, Modality modality, StageStyle style) {
		super(Optional.ofNullable(style).orElse(StageStyle.DECORATED));
		Optional.ofNullable(owner).ifPresent(e -> initOwner(e));
		Optional.ofNullable(modality).ifPresent(e -> initModality(e));
		final FXMLLoader loader = new FXMLLoader(getPathByController(controller));
		try {
			loader.setControllerFactory(new Callback<Class<?>, Object>() {
				@Override
				public Object call(Class<?> aClass) {
					return controller;
				}
			});
			setScene(new Scene((Parent) loader.load()));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}