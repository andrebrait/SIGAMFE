package com.sigamfe.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.configuration.ScreensConfiguration;
import com.sigamfe.configuration.util.FXMLDialog;
import com.sigamfe.controller.iface.DialogController;

public class LoginController implements DialogController {

	@Autowired
	private AuthenticationManager authenticationManager;
	private final ScreensConfiguration screens;
	private FXMLDialog dialog;

	public LoginController(ScreensConfiguration screens) {
		this.screens = screens;
	}

	@FXML
	public Label labelErro;

	@FXML
	public TextField username;

	@FXML
	public TextField password;

	@FXML
	public Label labelServidor;

	@FXML
	public void login() {
		Authentication authToken = new UsernamePasswordAuthenticationToken(username.getText(), password.getText());
		try {
			authToken = authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authToken);
		} catch (final AuthenticationException e) {
			labelErro.setText("Erro: usuário ou senha inválidos");
			return;
		}
		dialog.close();
	}

	@FXML
	public void changeServer() {

	}

	@Override
	public void setDialog(FXMLDialog dialog) {
		this.dialog = dialog;
	}

	private void updateLabelServidor() {
		this.labelServidor.setText(PersistenceConfiguration.getDB_HOSTNAME() + ":" + PersistenceConfiguration.getDB_PORT());
	}

}
