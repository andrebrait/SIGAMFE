package com.sigamfe.controller;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.model.Material;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EstoqueController {

	private Material entityMaterial;

	@FXML
	private TableView<Material> tableInserir;

	@FXML
	private TableColumn<Material, Integer> tableInserirQuantidade;

	@FXML
	private Button buttonRemoverMaterial;

	@FXML
	private TableView<Material> tableConsultar;

	public void initializeWindow() {
		entityMaterial = new Material() {

			private static final long serialVersionUID = -1342694126956506751L;

		};

		// Tabela Inserir
		tableInserir.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableInserir.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldSelection, newSelection) -> buttonRemoverMaterial.setDisable(newSelection == null));
		tableInserir.getColumns().get(0).setCellFactory(ComboBoxTableCell.forTableColumn(entityMaterial));
		tableInserirQuantidade.setCellFactory(TextFieldTableCell.<Material> forTableColumn());
		tableInserirQuantidade.setOnEditCommit(t -> t.getRowValue().setQuantidade(t.getNewValue()));
		tableInserirQuantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
		tableInserirQuantidade.textProperty().addListener((obs, oldValue, newValue) -> TextFieldsUtils);

		// Tabela Consultar
		tableConsultar.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Material"));
		tableConsultar.getColumns().get(0).setEditable(false);
		tableConsultar.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
		tableConsultar.getColumns().get(1).setEditable(false);
	}
}
