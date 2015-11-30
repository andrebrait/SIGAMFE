package com.sigamfe.controller.base;

import java.io.Serializable;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.sigamfe.model.base.BaseEntity;

import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;

public interface BaseController extends Serializable {

	/**
	 * Método de inicialização da janela, contendo os comandos necessários para
	 * instanciar a janela apropriadamente. Deve ser anotado com {@code @FXML}
	 */
	void initializeWindow();

	/**
	 * Gets the parent stage.
	 *
	 * @param <T>
	 *            the generic type
	 * @param component
	 *            the component
	 * @return the parent stage
	 */
	default <T extends Node> Stage getParentStage(T component) {
		return (Stage) component.getScene().getWindow();
	}

	/**
	 * Generate object property.
	 *
	 * @param <T>
	 *            the generic type
	 * @param bean
	 *            the bean
	 * @param name
	 *            the name
	 * @return the property
	 */
	default <T> Property<T> retrieveProperty(Object bean, String name) {
		if (bean instanceof BaseEntity<?>) {
			return ((BaseEntity<?>) bean).getPropertyByName(name);
		}
		return null;
	}

	default String getOptionalText(String text) {
		return StringUtils.defaultIfBlank(text, null);
	}

	default void showError(String title, String message) {
		Alert al = new Alert(AlertType.ERROR);
		al.setTitle(StringUtils.defaultString(title, "Erro"));
		al.setHeaderText(null);
		al.setContentText(message);
		al.showAndWait();
	}

	default void showWarning(String title, String message) {
		Alert al = new Alert(AlertType.WARNING);
		al.setTitle(StringUtils.defaultString(title, "Aviso"));
		al.setHeaderText(null);
		al.setContentText(message);
		al.showAndWait();
	}

	default void showInformation(String title, String message) {
		Alert al = new Alert(AlertType.INFORMATION);
		al.setTitle(StringUtils.defaultString(title, "Informação"));
		al.setHeaderText(null);
		al.setContentText(message);
		al.showAndWait();
	}

	default boolean showConfirmation(String title, String message) {
		Alert al = new Alert(AlertType.CONFIRMATION);
		al.setTitle(StringUtils.defaultString(title, "Confirmação"));
		al.setHeaderText(null);
		al.setContentText(message);
		Optional<ButtonType> result = al.showAndWait();
		if (result.isPresent()) {
			return result.get().equals(ButtonType.OK);
		}
		return false;
	}

	default void clearAll(TextInputControl... controls) {
		for (TextInputControl t : controls) {
			t.clear();
		}
	}

	void loadEntity(BaseEntity<?> entity);

}
