package com.sigamfe.controller;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DisponibilidadeController {

	@FXML
	private TableView<String> tableDispHoje;

	@FXML
	private TableView<String> tableDispPerdidosHoje;

	@FXML
	private TableView<String> tableDispFutura;

	@FXML
	private DatePicker dataFutura;

	public void initializeWindow() {
		// Inserir inicialização da tabela
	}
}
