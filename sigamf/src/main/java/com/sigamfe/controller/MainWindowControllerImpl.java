package com.sigamfe.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Modality;

import javax.annotation.PostConstruct;

import lombok.Getter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.controller.base.FXMLDialog;

@Controller
@Lazy
public class MainWindowControllerImpl implements MainWindowController {

	private static final long serialVersionUID = 2706241759123410314L;

	@Getter
	private FXMLDialog dialog;

	@Override
	@PostConstruct
	public void initializeWindow() {
		dialog = new FXMLDialog(this, null, Modality.NONE);
		dialog.setMaximized(true);
		dialog.setTitle(Titles.MAIN_WINDOW_TITLE);
	}

	@Override
	@FXML
	public void sair() {
		Platform.exit();
	}
}
