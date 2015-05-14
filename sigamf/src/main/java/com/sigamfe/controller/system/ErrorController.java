package com.sigamfe.controller.system;

import javafx.fxml.FXML;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sigamfe.configuration.util.FXMLDialog;

@Controller
@Lazy
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ErrorController {
	private FXMLDialog dialog;

	public void setDialog(FXMLDialog dialog) {
		this.dialog = dialog;
	}

	@FXML
	public void close() {
		dialog.close();
	}
}
