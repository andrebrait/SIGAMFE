package com.sigamfe.controller.base;

import com.sigamfe.configuration.util.FXMLDialog;

public interface DialogController {
	FXMLDialog getDialog();

	void setDialog(FXMLDialog dialog);

	void initializeWindow();
}
