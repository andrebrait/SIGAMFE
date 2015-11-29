package com.sigamfe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.UsuarioBusiness;
import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.configuration.SigamfeContext;
import com.sigamfe.controller.base.BaseController;
import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.util.FilteredChangeListener;
import com.sigamfe.util.MaskValidator;
import com.sigamfe.util.TextFieldUtils;
import com.sigamfe.views.classes.MainWindowView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginController implements BaseController {

	private static final long serialVersionUID = -7412951282356817346L;

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@Autowired
	private MainWindowView mainWindowView;

	@FXML
	private Label labelErro;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Text textServidor;

	@Override
	public void initializeWindow() {
		updateLabelServidor();
		username.textProperty().addListener(new FilteredChangeListener(username, (newValue, oldValue) -> TextFieldUtils
				.processMask(newValue, oldValue, MaskValidator.USERNAME_PW_VALIDATOR)));
		password.textProperty().addListener(new FilteredChangeListener(password, (newValue, oldValue) -> TextFieldUtils
				.processMask(newValue, oldValue, MaskValidator.USERNAME_PW_VALIDATOR)));
	}

	@FXML
	public void login() {
		if (usuarioBusiness.login(username.getText(), password.getText())) {
			SigamfeContext.usuarioLogado = usuarioBusiness.findByLogin(username.getText());
			mainWindowView.newStage();
			mainWindowView.getCurrentStage().show();
			getParentStage(username).close();
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
	public void loadEntity(BaseEntity<?> entity) {
		// TODO Auto-generated method stub

	}

}
