/*
 *
 */
package com.sigamfe.views.classes.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ResourceUtils;

import com.sigamfe.controller.base.BaseController;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public abstract class AbstractFxmlView implements Serializable {

	private static final long serialVersionUID = -1L;

	public static final String VIEW_PATH = "classpath:com/sigamfe/views/";

	protected ObjectProperty<Object> controllerProperty;

	protected URL resource;

	/**
	 * New stage.
	 */
	public abstract void newStage();

	/**
	 * Gets the current stage.
	 *
	 * @return the current stage
	 */
	public abstract Stage getCurrentStage();

	/**
	 * Sets the current stage.
	 *
	 * @param currentStage
	 *            the new current stage
	 */
	protected abstract void setCurrentStage(Stage currentStage);

	@Autowired
	protected ConfigurableApplicationContext applicationContext;

	public AbstractFxmlView() {
		this.controllerProperty = new SimpleObjectProperty<>();
		try {
			this.resource = ResourceUtils.getURL(getFxmlName());
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private Object createControllerForType(Class<?> type) {
		return this.applicationContext.getBean(type);
	}

	FXMLLoader loadSynchronously(URL resource) throws IllegalStateException {

		FXMLLoader loader = new FXMLLoader(resource);
		loader.setControllerFactory(this::createControllerForType);

		try {
			loader.load();
		} catch (IOException ex) {
			throw new IllegalStateException("Cannot load " + getFxmlName(), ex);
		}

		return loader;
	}

	FXMLLoader initializeLoader() {
		FXMLLoader loader = loadSynchronously(resource);
		this.controllerProperty.set(loader.getController());
		return loader;
	}

	/**
	 * Initializes the view by loading the FXML (if not happened yet) and
	 * returns the top Node (parent) specified in the FXML file.
	 *
	 * @return
	 */
	public Parent getView() {
		return initializeLoader().getRoot();
	}

	/**
	 * In case the view was not initialized yet, the conventional fxml
	 * (airhacks.fxml for the AirhacksView and AirhacksPresenter) are loaded and
	 * the specified presenter / controller is going to be constructed and
	 * returned.
	 *
	 * @return the corresponding controller / presenter (usually for a
	 *         AirhacksView the AirhacksPresenter)
	 */
	public BaseController getController() {
		return (BaseController) this.controllerProperty.get();
	}

	/**
	 * @return the name of the fxml file derived from the FXML view. e.g. The
	 *         name for the AirhacksView is going to be airhacks.fxml.
	 */
	final String getFxmlName() {
		return VIEW_PATH + StringUtils.removeEndIgnoreCase(getClass().getSimpleName(), "view") + ".fxml";
	}

}