package com.sigamfe.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.annotation.PostConstruct;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sigamfe.business.UsuarioBusiness;
import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.configuration.constants.Messages;
import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.controller.base.FXMLDialog;

@Controller
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginControllerImpl implements LoginController {

	private static final long serialVersionUID = -7412951282356817346L;

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@Autowired
	private MainWindowController mainWindowController;

	@Getter
	private FXMLDialog dialog;

	@FXML
	private Label labelErro;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Text labelServidor;

	@Override
	@FXML
	public void login() {
		if (usuarioBusiness.login(username.getText(), password.getText())) {
			dialog.getOwnerAsStage().show();
			dialog.close();
		} else {
			labelErro.setText(Messages.LOGIN_INVALIDO);

		}
	}

	@Override
	@FXML
	public void changeServer() {
		// TODO Implementar esta funcionalidade
	}

	private void updateLabelServidor() {
		labelServidor.setText(PersistenceConfiguration.getDB_HOSTNAME() + ":" + PersistenceConfiguration.getDB_PORT());
	}

	@Override
	@PostConstruct
	public void initializeWindow() {
		dialog = new FXMLDialog(this, mainWindowController.getDialog());
		dialog.setOnCloseRequest(e -> Platform.exit());
		dialog.setResizable(false);
		dialog.setTitle(Titles.LOGIN_WINDOW_TITLE);
		updateLabelServidor();
		dialog.showAndWait();
	}

}
