package com.sigamfe.controller;

import javafx.stage.Modality;

import javax.annotation.PostConstruct;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.configuration.util.FXMLDialog;

@Component
@Lazy
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainWindowControllerImpl implements MainWindowController {

	@Getter
	@Setter
	private FXMLDialog dialog;

	@Override
	@PostConstruct
	public void initializeWindow() {
		setDialog(new FXMLDialog(this, null, Modality.NONE));
		dialog.setResizable(true);
		dialog.setMaximized(true);
		dialog.setTitle(Titles.MAIN_WINDOW_TITLE);
	}
}
