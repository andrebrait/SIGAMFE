package com.sigamfe.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.configuration.constants.Messages;
import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.controller.base.FXMLDialog;
import com.sigamfe.repository.UsuarioRepository;

@Component
@Lazy
public class LoginControllerImpl implements LoginController {

	@Autowired
	private MainWindowController mainWindowController;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Getter
	@Setter
	private FXMLDialog dialog;

	@FXML
	private Label labelErro;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Text labelServidor;

	public LoginControllerImpl() {
		this.dialog = new FXMLDialog(this);
	}

	@Override
	@FXML
	public void login() {
		Authentication authToken = new UsernamePasswordAuthenticationToken(username.getText(), password.getText());
		try {
			authToken = authenticationProvider.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authToken);
			mainWindowController.getDialog().show();
			getDialog().close();
		} catch (AuthenticationException e) {
			labelErro.setText(Messages.LOGIN_INVALIDO);
			throw new RuntimeException(e);
		}
	}

	@Override
	@FXML
	public void changeServer() {
		// TODO Implementar esta funcionalidade
	}

	@Override
	public void initializeWindow() {
		getDialog().initializeWindow();
		getDialog().setOnCloseRequest(e -> Platform.exit());
		getDialog().setResizable(false);
		getDialog().setTitle(Titles.LOGIN_WINDOW_TITLE);
		mainWindowController.initializeWindow();
		updateLabelServidor();
		getDialog().showAndWait();
	}

	private void updateLabelServidor() {
		labelServidor.setText(PersistenceConfiguration.getDB_HOSTNAME() + ":" + PersistenceConfiguration.getDB_PORT());
	}

}
