package com.sigamfe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.sigamfe.business.SystemBusiness;
import com.sigamfe.controller.base.BaseController;
import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.views.classes.CadastroView;
import com.sigamfe.views.classes.DisponibilidadeView;
import com.sigamfe.views.classes.EstoqueView;

import javafx.fxml.FXML;

@Component
@Lazy
public class MainWindowController implements BaseController {

	private static final long serialVersionUID = 2706241759123410314L;

	@Autowired
	private SystemBusiness systemBusiness;

	@Autowired
	private CadastroView cadastroView;

	@Autowired
	private DisponibilidadeView disponibilidadeView;

	@Autowired
	private EstoqueView estoqueView;

	@Override
	public void initializeWindow() {

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
		cadastroView.newStage();
		cadastroView.getCurrentStage().showAndWait();
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
		disponibilidadeView.newStage();
		disponibilidadeView.getCurrentStage().showAndWait();
	}

	@FXML
	public void estoqueInserir() {
		estoqueView.newStage();
		estoqueView.getCurrentStage().showAndWait();

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
