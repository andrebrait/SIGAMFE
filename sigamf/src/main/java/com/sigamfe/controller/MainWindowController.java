package com.sigamfe.controller;

import javafx.stage.Modality;

import javax.annotation.PostConstruct;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.configuration.util.FXMLDialog;
import com.sigamfe.controller.iface.DialogController;

@Controller
@Lazy
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainWindowController implements DialogController {

	@Getter
	@Setter
	private FXMLDialog dialog;

	@PostConstruct
	public void initialize() {
		setDialog(new FXMLDialog(this, Modality.NONE, null));
		dialog.setResizable(true);
		dialog.setMaximized(true);
		dialog.setTitle(Titles.MAIN_WINDOW_TITLE);
	}
}
