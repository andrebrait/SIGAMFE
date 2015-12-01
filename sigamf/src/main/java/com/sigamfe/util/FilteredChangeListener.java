package com.sigamfe.util;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class FilteredChangeListener implements ChangeListener<String> {

	public static interface Filter {

		public String getFiltered(String newValue, String oldValue);

	}

	private TextField textField;
	private Filter filter;
	private Property<String> textProperty;

	public FilteredChangeListener(TextField textField, Filter filter) {
		this.textField = textField;
		this.filter = filter;
		this.textProperty = textField.textProperty();
	}

	public FilteredChangeListener(Property<String> textProperty, Filter filter) {
		this.textProperty = textProperty;
		this.filter = filter;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		textProperty.removeListener(this);
		textProperty.setValue(filter.getFiltered(newValue, oldValue));
		if (textField != null) {
			textField.selectEnd();
			textField.deselect();
		}
		textProperty.addListener(this);
	}

}
