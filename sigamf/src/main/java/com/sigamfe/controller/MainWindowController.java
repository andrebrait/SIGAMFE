package com.sigamfe.controller;

import com.sigamfe.business.SystemBusiness;
import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.controller.base.BaseController;
import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.views.classes.base.ViewStage;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Modality;
import lombok.Getter;

public class MainWindowController implements BaseController {

	private static final long serialVersionUID = 2706241759123410314L;

	public MainWindowController() {
		initializeWindow();
	}

	@Getter
	private ViewStage stage;

	public SystemBusiness systemBusiness = context().getBean(SystemBusiness.class);

	@Override
	public void initializeWindow() {
		stage = new ViewStage(this, null, Modality.NONE);
		stage.setMaximized(true);
		stage.setOnCloseRequest(e -> Platform.exit());
		stage.setTitle(Titles.WINDOW_MAIN);
	}

	@FXML
	public void pedidoAbrir() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void pedidoPesquisar() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void pedidoFechar() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void cadastroCadastrar() {
		new CadastroController();
	}

	@FXML
	public void cadastroPesquisar() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void relatorioGerar() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void relatorioPesquisar() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void estoqueDisponibilidade() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void estoqueInserir() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void estoqueConsultaTotal() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void ajudaSobre() {
		systemBusiness.about();
	}

	@Override
	public void loadEntity(BaseEntity<?> entity) {
		// TODO Auto-generated method stub

	}
}
