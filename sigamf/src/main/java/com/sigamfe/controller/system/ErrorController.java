package com.sigamfe.controller.system;

import javafx.fxml.FXML;

import com.sigamfe.configuration.util.FXMLDialog;
import com.sigamfe.controller.iface.DialogController;

public class ErrorController implements DialogController {
	private FXMLDialog dialog;

	public void setDialog(FXMLDialog dialog) {
		this.dialog = dialog;
	}

	@FXML
	public void close() {
		dialog.close();
	}
}
