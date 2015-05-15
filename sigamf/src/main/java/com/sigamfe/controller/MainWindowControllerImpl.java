package com.sigamfe.controller;

import javafx.stage.Modality;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.controller.base.FXMLDialog;

@Component
@Lazy
public class MainWindowControllerImpl implements MainWindowController {

	@Setter
	@Getter
	private FXMLDialog dialog;

	public MainWindowControllerImpl() {
		this.dialog = new FXMLDialog(this);
	}

	@Override
	public void initializeWindow() {
		getDialog().initializeWindow(null, Modality.NONE, null);
		getDialog().setMaximized(true);
		getDialog().setTitle(Titles.MAIN_WINDOW_TITLE);
	}
}
