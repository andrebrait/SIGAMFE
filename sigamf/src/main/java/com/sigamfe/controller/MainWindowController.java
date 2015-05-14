package com.sigamfe.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import com.sigamfe.configuration.ScreensConfiguration;
import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.configuration.util.FXMLMainWindow;

public class MainWindowController implements Initializable {

	private final ScreensConfiguration screens;

	private FXMLMainWindow dialog;

	public MainWindowController(ScreensConfiguration screens) {
		this.screens = screens;
	}

	public void setDialog(FXMLMainWindow dialog) {
		dialog.setResizable(true);
		dialog.setMaximized(true);
		dialog.setTitle(Titles.MAIN_WINDOW_TITLE);
		this.dialog = dialog;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
