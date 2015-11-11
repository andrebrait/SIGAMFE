package com.sigamfe.controller.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import lombok.Getter;

public class ViewStage extends Stage implements Serializable {

	private static final long serialVersionUID = 5712944198502672787L;

	public static final String VIEW_PATH = "classpath:com/sigamfe/views/";

	@Getter
	private final BaseController controller;

	private URL getPathByController(Class<? extends BaseController> controller) {
		try {
			return ResourceUtils.getURL(
					VIEW_PATH + StringUtils.removeEndIgnoreCase(controller.getSimpleName(), "Controller") + ".fxml");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public ViewStage(BaseController controller) {
		this(controller, null);
	}

	public ViewStage(BaseController controller, Window owner) {
		this(controller, owner, null, null);
	}

	public ViewStage(BaseController controller, Window owner, Modality modality) {
		this(controller, owner, modality, null);
	}

	public ViewStage(BaseController controller, Window owner, Modality modality, StageStyle style) {
		super();
		this.controller = controller;
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

	public Stage getOwnerAsStage() {
		return (Stage) getOwner();
	}
}
