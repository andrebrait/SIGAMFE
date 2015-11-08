package com.sigamfe.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.controller.base.ViewStage;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Modality;
import lombok.Getter;

@Controller
@Lazy
public class MainWindowControllerImpl implements MainWindowController {

	private static final long serialVersionUID = 2706241759123410314L;

	@Getter
	private ViewStage stage;

	@Autowired
	public ConfigurableApplicationContext applicationContext;

	@Override
	@PostConstruct
	public void initializeWindow() {
		stage = new ViewStage(this, null, Modality.NONE);
		stage.setMaximized(true);
		stage.setOnCloseRequest(e -> Platform.exit());
		stage.setTitle(Titles.MAIN_WINDOW_TITLE);
	}

	@FXML
	@Override
	public void pedidoAbrir() {
		// TODO Auto-generated method stub

	}

	@FXML
	@Override
	public void pedidoPesquisar() {
		// TODO Auto-generated method stub

	}

	@FXML
	@Override
	public void pedidoFechar() {
		// TODO Auto-generated method stub

	}

	@FXML
	@Override
	public void cadastroCadastrar() {
		applicationContext.getBean(CadastroController.class);
	}

	@FXML
	@Override
	public void cadastroPesquisar() {
		// TODO Auto-generated method stub

	}

	@FXML
	@Override
	public void relatorioGerar() {
		// TODO Auto-generated method stub

	}

	@FXML
	@Override
	public void relatorioPesquisar() {
		// TODO Auto-generated method stub

	}

	@FXML
	@Override
	public void estoqueDisponibilidade() {
		// TODO Auto-generated method stub

	}

	@FXML
	@Override
	public void estoqueInserir() {
		// TODO Auto-generated method stub

	}

	@FXML
	@Override
	public void estoqueConsultaTotal() {
		// TODO Auto-generated method stub

	}

	@FXML
	@Override
	public void ajudaSobre() {
		// TODO Auto-generated method stub

	}
}
