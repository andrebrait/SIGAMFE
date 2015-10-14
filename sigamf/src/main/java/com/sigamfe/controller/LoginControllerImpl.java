package com.sigamfe.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sigamfe.business.UsuarioBusiness;
import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.controller.base.ViewStage;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Getter;

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
	private ViewStage stage;

	@FXML
	private Label labelErro;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Text textServidor;

	@Override
	@FXML
	public void login() {
		if (usuarioBusiness.login(username.getText(), password.getText())) {
			stage.getOwnerAsStage().show();
			stage.close();
		} else {
			labelErro.setVisible(true);
		}
	}

	@Override
	@FXML
	public void changeServer() {
		// TODO Implementar esta funcionalidade
	}

	private void updateLabelServidor() {
		textServidor.setText(PersistenceConfiguration.getDB_HOSTNAME() + ":" + PersistenceConfiguration.getDB_PORT());
	}

	@Override
	@PostConstruct
	public void initializeWindow() {
		stage = new ViewStage(this, mainWindowController.getStage());
		stage.setOnCloseRequest(e -> Platform.exit());
		stage.setResizable(false);
		stage.setTitle(Titles.LOGIN_WINDOW_TITLE);
		updateLabelServidor();
		stage.showAndWait();
	}

}
