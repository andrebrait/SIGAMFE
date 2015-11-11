package com.sigamfe.controller;

import javax.annotation.PostConstruct;

import com.sigamfe.business.UsuarioBusiness;
import com.sigamfe.configuration.ApplicationConfiguration;
import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.controller.base.BaseController;
import com.sigamfe.controller.base.ViewStage;
import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.util.FilteredChangeListener;
import com.sigamfe.util.MaskValidator;
import com.sigamfe.util.TextFieldUtils;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Getter;

public class LoginController implements BaseController {

	private static final long serialVersionUID = -7412951282356817346L;

	public LoginController() {
		initializeWindow();
	}

	private UsuarioBusiness usuarioBusiness = context().getBean(UsuarioBusiness.class);

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

	@FXML
	public void login() {
		if (usuarioBusiness.login(username.getText(), password.getText())) {
			ApplicationConfiguration.mainWindowController = new MainWindowController();
			ApplicationConfiguration.mainWindowController.getStage().show();
			ApplicationConfiguration.usuarioLogado = usuarioBusiness.findByLogin(username.getText());
			stage.close();
		} else {
			labelErro.setVisible(true);
		}
	}

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
		stage = new ViewStage(this);
		stage.setOnCloseRequest(e -> Platform.exit());
		stage.setResizable(false);
		stage.setTitle(Titles.WINDOW_LOGIN);
		updateLabelServidor();
		username.textProperty().addListener(new FilteredChangeListener(username, (newValue, oldValue) -> TextFieldUtils
				.processMask(newValue, oldValue, MaskValidator.USERNAME_PW_VALIDATOR)));
		password.textProperty().addListener(new FilteredChangeListener(password, (newValue, oldValue) -> TextFieldUtils
				.processMask(newValue, oldValue, MaskValidator.USERNAME_PW_VALIDATOR)));
		stage.showAndWait();
	}

	@Override
	public void loadEntity(BaseEntity<?> entity) {
		// TODO Auto-generated method stub

	}

}
