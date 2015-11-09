package com.sigamfe.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public abstract class FilteredChangeListener implements ChangeListener<String> {

	private TextField textField;

	private boolean changed = false;

	protected abstract String getFiltered(String newValue, String oldValue);

	public FilteredChangeListener(TextField textField) {
		this.textField = textField;
	}

	private boolean isChanged() {
		changed = !changed;
		return !changed;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (!isChanged()) {
			textField.setText(getFiltered(newValue, oldValue));
		}
	}

}
