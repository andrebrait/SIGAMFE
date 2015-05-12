package com.sigamfe.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.sigamfe.configuration.ScreensConfiguration;
import com.sigamfe.controller.base.FXMLDialog;
import com.sigamfe.controller.iface.DialogController;

@Controller
public class LoginController implements DialogController {

	@Autowired
	private AuthenticationManager authenticationManager;
	private final ScreensConfiguration screens;

	@Setter
	private FXMLDialog dialog;

	public LoginController(ScreensConfiguration screens) {
		this.screens = screens;
	}

	@FXML
	private Label header;

	@FXML
	private TextField username;

	@FXML
	private TextField password;

	@FXML
	public void login() {
		Authentication authToken = new UsernamePasswordAuthenticationToken(username.getText(), password.getText());
		try {
			authToken = authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authToken);
		} catch (final AuthenticationException e) {
			header.setText("Usuário ou senha errados. Tente novamente.");
			header.setTextFill(Color.DARKRED);
			return;
		}
		dialog.close();
		screens.showScreen(screens.customerDataScreen());
	}

}
