package com.sigamfe.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.configuration.ScreensConfiguration;
import com.sigamfe.configuration.util.FXMLDialog;
import com.sigamfe.controller.iface.DialogController;

public class LoginController implements DialogController, Initializable {

	private final ScreensConfiguration screens;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	private FXMLDialog dialog;

	@FXML
	private Label labelErro;

	@FXML
	private TextField username;

	@FXML
	private TextField password;

	@FXML
	private Text labelServidor;

	public LoginController(ScreensConfiguration screens) {
		this.screens = screens;
	}

	@Override
	public void setDialog(FXMLDialog dialog) {
		dialog.setOnCloseRequest(e -> Platform.exit());
		dialog.setResizable(false);
		this.dialog = dialog;
	}

	@FXML
	public void login() {
		Authentication authToken = new UsernamePasswordAuthenticationToken(username.getText(), password.getText());
		try {
			authToken = authenticationProvider.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authToken);
		} catch (final AuthenticationException e) {
			labelErro.setText("Erro: usuário ou senha inválidos");
			return;
		}
	}

	@FXML
	public void changeServer() {

	}

	private void updateLabelServidor() {
		this.labelServidor.setText(PersistenceConfiguration.getDB_HOSTNAME() + ":" + PersistenceConfiguration.getDB_PORT());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateLabelServidor();
	}
}
