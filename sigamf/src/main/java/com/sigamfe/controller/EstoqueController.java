package com.sigamfe.controller;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.controller.base.BaseController;
import com.sigamfe.model.Material;
import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.util.FilteredChangeListener;
import com.sigamfe.util.TextFieldUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EstoqueController implements BaseController {

	private static final long serialVersionUID = 6485441790538865501L;

	private Material entityMaterial;

	@FXML
	private TableView<Material> tableInserir;

	@FXML
	private TableColumn<Material, Integer> tableInserirQuantidade;

	@FXML
	private Button buttonRemoverMaterial;

	@FXML
	private TableView<Material> tableConsultar;

	@Override
	public void initializeWindow() {
		entityMaterial = new Material();

		// Tabela Inserir
		tableInserir.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableInserir.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldSelection, newSelection) -> buttonRemoverMaterial.setDisable(newSelection == null));
		// tableInserir.getColumns().get(0).setCellFactory(ComboBoxTableCell.forTableColumn(entityMaterial));
		// tableInserirQuantidade.setCellFactory(TextFieldTableCell.<Material>
		// forTableColumn());
		tableInserirQuantidade.setOnEditCommit(t -> t.getRowValue().setQuantidade(t.getNewValue()));
		tableInserirQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tableInserirQuantidade.textProperty()
				.addListener(new FilteredChangeListener(tableInserirQuantidade.textProperty(),
						(newValue, oldValue) -> TextFieldUtils.processMaxDecimal(newValue, oldValue, 0, 7, 0)));

		// Tabela Consultar
		tableConsultar.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("material"));
		tableConsultar.getColumns().get(0).setEditable(false);
		tableConsultar.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tableConsultar.getColumns().get(1).setEditable(false);
	}

	@Override
	public void loadEntity(BaseEntity<?> entity) {
		// TODO Auto-generated method stub

	}
}
