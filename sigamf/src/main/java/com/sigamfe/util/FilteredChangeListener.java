package com.sigamfe.util;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class FilteredChangeListener implements ChangeListener<String> {

	public static interface Filter {

		public String getFiltered(String newValue, String oldValue);

	}

	private TextField textField;
	private Filter filter;

	public FilteredChangeListener(TextField textField, Filter filter) {
		this.textField = textField;
		this.filter = filter;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		Platform.runLater(() -> {
			textField.setText(filter.getFiltered(newValue, oldValue));
			textField.selectEnd();
			textField.deselect();
		});
	}

}
