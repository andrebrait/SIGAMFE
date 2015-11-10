package com.sigamfe.controller;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sigamfe.configuration.constants.Labels;
import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.controller.base.ViewStage;
import com.sigamfe.model.Cliente;
import com.sigamfe.model.ClientePF;
import com.sigamfe.model.ClientePJ;
import com.sigamfe.model.Endereco;
import com.sigamfe.model.Material;
import com.sigamfe.model.TelefoneCliente;
import com.sigamfe.model.Usuario;
import com.sigamfe.model.enums.IndicadorUnidade;
import com.sigamfe.model.enums.converter.javafx.FxEnumConverter;
import com.sigamfe.util.FilteredChangeListener;
import com.sigamfe.util.MaskValidator;
import com.sigamfe.util.TextFieldUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import lombok.Getter;

@Controller
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CadastroControllerImpl implements CadastroController {

	private static final long serialVersionUID = -3277976711219254609L;

	private Cliente entityCliente;

	private Material entityMaterial;

	private Usuario entityUsuario;

	@Getter
	private ViewStage stage;

	@Autowired
	private MainWindowController mainWindowController;

	@FXML
	private ToggleGroup tipoPessoa;

	@FXML
	private Label labelClienteTipoPessoa;

	@FXML
	private RadioButton radioClientePessoaFisica;

	@FXML
	private RadioButton radioClientePessoaJuridica;

	@FXML
	private TextField textClienteCpf;

	@FXML
	private TextField textClienteNome;

	@FXML
	private TextField textClienteRg;

	@FXML
	private TextField textClienteCnh;

	@FXML
	private TextField textClienteEndereco;

	@FXML
	private TextField textClienteNumero;

	@FXML
	private TextField textClienteCidade;

	@FXML
	private TextField textClienteEmail;

	@FXML
	private TextField textClienteCep;

	@FXML
	private TextField textClienteUf;

	@FXML
	private TableView<TelefoneCliente> tableClienteTelefones;

	@FXML
	private Button buttonRemoverTelefone;

	@FXML
	private ToggleGroup bloqueadoGroup;

	@FXML
	private RadioButton radioClienteBloqueadoSim;

	@FXML
	private RadioButton radioClienteBloqueadoNao;

	private boolean isPessoaFisica() {
		return radioClientePessoaFisica.isSelected();
	}

	@Override
	@FXML
	public void pesquisaClienteNome() {

	}

	@Override
	@FXML
	public void pesquisaClienteCpfCnpj() {

	}

	@Override
	@FXML
	public void onChangeTogglePessoaCliente() {
		labelClienteTipoPessoa
				.setText(isPessoaFisica() ? Labels.CPF + Labels.OBRIGATORIO : Labels.CNPJ + Labels.OBRIGATORIO);
	}

	@Override
	@FXML
	public void pesquisaClienteRg() {

	}

	@Override
	@FXML
	public void pesquisaClienteEnderecoCep() {

	}

	@Override
	@FXML
	public void adicionaClienteTelefone() {
		TextInputDialog dialogNovoTelefone = new TextInputDialog();
		dialogNovoTelefone.setTitle(Titles.DIALOG_NOVO_TELEFONE);
		dialogNovoTelefone.setHeaderText("Por favor, insira o novo telefone.");
		dialogNovoTelefone.setContentText(null);
		dialogNovoTelefone.getEditor().textProperty()
				.addListener(new FilteredChangeListener(dialogNovoTelefone.getEditor(),
						(newValue, oldValue) -> MaskValidator.getVersionByLength(newValue, oldValue,
								MaskValidator.TELEFONE_8_VALIDATOR, MaskValidator.TELEFONE_9_VALIDATOR)));
		Optional<String> result = dialogNovoTelefone.showAndWait();
		result.ifPresent((str) -> {
			TelefoneCliente tc = new TelefoneCliente();
			tc.setCliente(entityCliente);
			tc.setTelefone(str);
			tableClienteTelefones.getItems().add(tc);
		});
	}

	@Override
	@FXML
	public void removeClienteTelefone() {
		tableClienteTelefones.getItems().removeAll(tableClienteTelefones.getSelectionModel().getSelectedItems());
	}

	@Override
	@FXML
	public void salvaCliente() {
		if (entityCliente == null) {
			entityCliente = isPessoaFisica() ? new ClientePF() : new ClientePJ();
		}
		if (isPessoaFisica()) {
			((ClientePF) entityCliente).setCpf(textClienteCpf.getText());
			((ClientePF) entityCliente).setRg(textClienteRg.getText());
			if (StringUtils.isNotBlank(textClienteCnh.getText())) {
				((ClientePF) entityCliente).setCnh(Long.parseLong(textClienteCnh.getText()));
			}
		} else {
			((ClientePJ) entityCliente).setCnpj(textClienteCpf.getText());
		}

		Endereco end = entityCliente.getEndereco();
		if (end == null) {
			end = new Endereco();
		}
		end.setCidade(textClienteCidade.getText());
		end.setUf(textClienteUf.getText());
		end.setLogradouro(textClienteEndereco.getText());
		// Atributos opcionais devem ser nulos se deixados em branco, por isso o
		// uso do getOptionalText.
		end.setCep(getOptionalText(textClienteCep.getText()));
		end.setNumero(getOptionalText(textClienteNumero.getText()));

		entityCliente.setTelefones(tableClienteTelefones.getItems());

	}

	@Override
	@FXML
	public void excluiCliente() {

	}

	@Override
	@FXML
	public void cancelar() {
		getStage().close();
	}

	/*
	 * Aqui começa a parte do controller dedicada à tela de material.
	 */

	@FXML
	private StackPane paneImagemMaterial;

	@FXML
	private ImageView imagemMaterial;

	@FXML
	private TextField textMaterialCodigo;

	@FXML
	private TextField textMaterialDescricao;

	@FXML
	private TextField textMaterialAluguel;

	@FXML
	private TextField textMaterialReposicao;

	@FXML
	private ComboBox<IndicadorUnidade> comboMaterialUnidade;

	@Override
	@FXML
	public void removeImagemMaterial() {
	}

	@Override
	@FXML
	public void adicionarImagemMaterial() {

	}

	@Override
	@FXML
	public void buscaMaterialCodigo() {

	}

	@Override
	@FXML
	public void buscaMaterialDescricao() {

	}

	@Override
	@FXML
	public void salvarMaterial() {

	}

	@Override
	@FXML
	public void excluirMaterial() {

	}

	@Override
	@PostConstruct
	public void initializeWindow() {
		stage = new ViewStage(this, mainWindowController.getStage());
		stage.setTitle(Titles.WINDOW_CADASTRO);
		stage.setResizable(false);

		// Inicializando o combo de unidade de materiais
		comboMaterialUnidade.getItems().addAll(IndicadorUnidade.values());
		comboMaterialUnidade.setConverter(new FxEnumConverter<>(IndicadorUnidade.class));

		tableClienteTelefones.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableClienteTelefones.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldSelection, newSelection) -> buttonRemoverTelefone.setDisable(newSelection == null));
		tableClienteTelefones.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tableClienteTelefones.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("observacoes"));
		tableClienteTelefones.getColumns().get(1).textProperty()
				.addListener((obs, oldValue, newValue) -> TextFieldUtils.processMaxChars(newValue, oldValue, 200));

		textClienteCpf.textProperty()
				.addListener(new FilteredChangeListener(textClienteCpf,
						(newValue, oldValue) -> TextFieldUtils.processMask(newValue, oldValue,
								isPessoaFisica() ? MaskValidator.CPF_VALIDATOR : MaskValidator.CNPJ_VALIDATOR)));
		textClienteCep.textProperty().addListener(new FilteredChangeListener(textClienteCep,
				(newValue, oldValue) -> TextFieldUtils.processMask(newValue, oldValue, MaskValidator.CEP_VALIDATOR)));
		textClienteRg.textProperty()
				.addListener(new FilteredChangeListener(textClienteRg,
						(newValue, oldValue) -> MaskValidator.getVersionByInsertedChar(newValue, oldValue,
								MaskValidator.RG_VALIDATOR_1_LETRA, MaskValidator.RG_VALIDATOR_2_LETRAS)));
		textClienteCnh.textProperty().addListener(new FilteredChangeListener(textClienteCnh,
				(newValue, oldValue) -> TextFieldUtils.processMask(newValue, oldValue, MaskValidator.CNH_VALIDATOR)));
		textMaterialAluguel.textProperty().addListener(new FilteredChangeListener(textMaterialAluguel,
				(newValue, oldValue) -> TextFieldUtils.processMaxDecimal(newValue, oldValue, 1, 6, 2)));
		textMaterialReposicao.textProperty().addListener(new FilteredChangeListener(textMaterialReposicao,
				(newValue, oldValue) -> TextFieldUtils.processMaxDecimal(newValue, oldValue, 1, 6, 2)));

		stage.showAndWait();
	}

	/*
	 * Usuário
	 */

	@FXML
	private TextField textUsuarioCpf;

	private TextField textUsuarioLogin;

	private TextField textUsuarioSenha;

	private TextField textUsuarioTelefone;

	@Override
	public void pesquisaUsuarioLogin() {

	}

	@Override
	public void pesquisaUsuarioCPF() {

	}

	@Override
	public void pesquisaUsuarioTelefone() {

	}

	@Override
	public void pesquisaUsuarioPermissao() {

	}

	@Override
	public void excluirUsuario() {

	}

	@Override
	public void salvaUsuario() {
		if (entityUsuario == null) {
			entityUsuario = new Usuario();
		}
		entityUsuario.setCpf(textUsuarioCpf.getText());
		entityUsuario.setLogin(textUsuarioLogin.getText());
		entityUsuario.setSenha(textUsuarioSenha.getText());
		entityUsuario.setTelefone(Long.parseLong(textUsuarioTelefone.getText()));
	}

}
