package com.sigamfe.controller;

import java.util.Optional;

import javax.annotation.PostConstruct;

import com.sigamfe.business.ClientePFBusiness;
import com.sigamfe.business.ClientePJBusiness;
import com.sigamfe.configuration.ApplicationConfiguration;
import com.sigamfe.configuration.constants.Messages;
import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.controller.base.BaseController;
import com.sigamfe.controller.base.ViewStage;
import com.sigamfe.model.Cliente;
import com.sigamfe.model.ClientePF;
import com.sigamfe.model.ClientePJ;
import com.sigamfe.model.Endereco;
import com.sigamfe.model.Material;
import com.sigamfe.model.TelefoneCliente;
import com.sigamfe.model.Usuario;
import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.IndicadorUnidade;
import com.sigamfe.model.enums.converter.javafx.FxEnumConverter;
import com.sigamfe.util.FilteredChangeListener;
import com.sigamfe.util.MaskValidator;
import com.sigamfe.util.TelefoneUtils;
import com.sigamfe.util.TextFieldUtils;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import lombok.Getter;

public class CadastroController implements BaseController {

	private static final long serialVersionUID = -3277976711219254609L;

	public CadastroController() {
		initializeWindow();
	}

	private Cliente entityCliente;

	private Endereco endereco;

	private Material entityMaterial;

	private Usuario entityUsuario;

	@Getter
	private ViewStage stage;

	private ClientePFBusiness clientePFBusiness = context().getBean(ClientePFBusiness.class);

	private ClientePJBusiness clientePJBusiness = context().getBean(ClientePJBusiness.class);

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
	private Button buttonPesquisaClienteRg;

	@FXML
	private TableView<TelefoneCliente> tableClienteTelefones;

	@FXML
	private TableColumn<TelefoneCliente, String> tableColumnClienteObservacoes;

	@FXML
	private Button buttonRemoverTelefone;

	@FXML
	private ToggleGroup bloqueadoGroup;

	@FXML
	private RadioButton radioClienteBloqueadoSim;

	@FXML
	private RadioButton radioClienteBloqueadoNao;

	private void resetScreen() {
		new Cliente() {

			private static final long serialVersionUID = -8479948750805801165L;

		}.copyProperties(entityCliente);
		new Endereco().copyProperties(endereco);
		tableClienteTelefones.getItems().clear();
		radioClienteBloqueadoNao.setDisable(false);
		radioClienteBloqueadoSim.setDisable(false);
		radioClientePessoaFisica.setDisable(false);
		radioClientePessoaJuridica.setDisable(false);
		tipoPessoa.selectToggle(radioClientePessoaFisica);
		bloqueadoGroup.selectToggle(radioClienteBloqueadoNao);
	}

	private boolean isPessoaFisica() {
		return tipoPessoa.getSelectedToggle().equals(radioClientePessoaFisica);
	}

	@FXML
	public void pesquisaClienteNome() {

	}

	@FXML
	public void pesquisaClienteCpfCnpj() {

	}

	@FXML
	public void pesquisaClienteRg() {

	}

	@FXML
	public void pesquisaClienteEnderecoCep() {

	}

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
			if (!tableClienteTelefones.getItems().contains(tc)) {
				tableClienteTelefones.getItems().add(tc);
			} else {
				showError(null, "Já existe um telefone com este número para este cliente!");
			}
		});
	}

	@FXML
	public void removeClienteTelefone() {
		if (showConfirmation("Excluir telefone", Messages.CONFIRM_EXCLUSAO)) {
			tableClienteTelefones.getItems().removeAll(tableClienteTelefones.getSelectionModel().getSelectedItems());
		}
	}

	@FXML
	public void salvaCliente() {
		Cliente toSave = isPessoaFisica() ? new ClientePF() : new ClientePJ();
		entityCliente.copyProperties(toSave);
		Endereco end = entityCliente.getEndereco();

		if (end == null)

		{
			end = new Endereco();
		}
		end.setCidade(textClienteCidade.getText());
		end.setUf(textClienteUf.getText());
		end.setLogradouro(textClienteEndereco.getText());
		// Atributos opcionais devem ser nulos se deixados em branco, por isso o
		// uso do getOptionalText.
		end.setCep(

				getOptionalText(textClienteCep.getText()));
		end.setNumero(getOptionalText(textClienteNumero.getText()));

		entityCliente.setTelefones(tableClienteTelefones.getItems());

	}

	@FXML
	public void excluiCliente() {
		if (entityCliente.getId() != null) {
			if (showConfirmation("Excluir cliente", Messages.CONFIRM_EXCLUSAO)) {
				if (isPessoaFisica()) {
					clientePFBusiness.delete(entityCliente.getId());
				} else {
					clientePJBusiness.delete(entityCliente.getId());
				}
			}
		} else {
			resetScreen();
		}
	}

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

	@FXML
	public void removeImagemMaterial() {
	}

	@FXML
	public void adicionarImagemMaterial() {

	}

	@FXML
	public void buscaMaterialCodigo() {

	}

	@FXML
	public void buscaMaterialDescricao() {

	}

	@FXML
	public void salvarMaterial() {

	}

	@FXML
	public void excluirMaterial() {

	}

	@Override
	@PostConstruct
	public void initializeWindow() {

		entityCliente = new Cliente() {

			private static final long serialVersionUID = -8479948750805801165L;

		};
		entityMaterial = new Material();
		entityUsuario = new Usuario();
		endereco = new Endereco();

		stage = new ViewStage(this, ApplicationConfiguration.mainWindowController.getStage());
		stage.setTitle(Titles.WINDOW_CADASTRO);
		stage.setResizable(false);

		// Inicializando o combo de unidade de materiais
		comboMaterialUnidade.getItems().addAll(IndicadorUnidade.values());
		comboMaterialUnidade.setConverter(new FxEnumConverter<>(IndicadorUnidade.class));
		comboMaterialUnidade.valueProperty().bindBidirectional(new SimpleObjectProperty<>(entityMaterial, "unidade"));

		tipoPessoa.selectedToggleProperty().addListener((obs, oldValue, newValue) -> {
			boolean pf = newValue.equals(radioClientePessoaFisica);
			textClienteRg.setDisable(!pf);
			textClienteCnh.setDisable(!pf);
			buttonPesquisaClienteRg.setDisable(!pf);
			if (!pf) {
				textClienteRg.clear();
				textClienteCnh.clear();
			}
		});

		// Setando os filtros e eventos de edição da tabela de telefones.
		tableClienteTelefones.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableClienteTelefones.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldSelection, newSelection) -> buttonRemoverTelefone.setDisable(newSelection == null));
		tableClienteTelefones.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tableClienteTelefones.getColumns().get(0).setEditable(false);
		tableColumnClienteObservacoes.setCellFactory(TextFieldTableCell.<TelefoneCliente> forTableColumn());
		tableColumnClienteObservacoes.setOnEditCommit(t -> t.getRowValue().setObservacoes(t.getNewValue()));
		tableColumnClienteObservacoes.setCellValueFactory(new PropertyValueFactory<>("observacoes"));
		tableColumnClienteObservacoes.textProperty()
				.addListener((obs, oldValue, newValue) -> TextFieldUtils.processMaxChars(newValue, 200));

		textClienteCpf.textProperty()
				.addListener(new FilteredChangeListener(textClienteCpf,
						(newValue, oldValue) -> TextFieldUtils.processMask(newValue, oldValue,
								isPessoaFisica() ? MaskValidator.CPF_VALIDATOR : MaskValidator.CNPJ_VALIDATOR)));
		textClienteCpf.textProperty().bindBidirectional(generateStringProperty(entityCliente, "cp"));

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

		textClienteNome.textProperty().addListener(new FilteredChangeListener(textClienteNome,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 100)));
		textClienteCidade.textProperty().addListener(new FilteredChangeListener(textClienteCidade,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 200)));
		textClienteUf.textProperty().addListener(new FilteredChangeListener(textClienteUf,
				(newValue, oldValue) -> TextFieldUtils.processMask(newValue, oldValue, MaskValidator.UF_VALIDATOR)));
		textClienteNumero.textProperty().addListener(new FilteredChangeListener(textClienteNumero,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 30)));
		textClienteEndereco.textProperty().addListener(new FilteredChangeListener(textClienteEndereco,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 200)));
		textClienteEmail.textProperty().addListener(new FilteredChangeListener(textClienteEmail,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 200)));

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

	public void pesquisaUsuarioLogin() {

	}

	public void pesquisaUsuarioCPF() {

	}

	public void pesquisaUsuarioTelefone() {

	}

	public void pesquisaUsuarioPermissao() {

	}

	public void excluirUsuario() {

	}

	public void salvaUsuario() {
		if (entityUsuario == null) {
			entityUsuario = new Usuario();
		}
		entityUsuario.setCpf(textUsuarioCpf.getText());
		entityUsuario.setLogin(textUsuarioLogin.getText());
		entityUsuario.setSenha(textUsuarioSenha.getText());
		entityUsuario.setTelefone(TelefoneUtils.getTelefoneAsLong(textUsuarioTelefone.getText()));
	}

	@Override
	public void loadEntity(BaseEntity<?> entity) {
		BaseEntity<?> target = null;
		if (entity instanceof Cliente) {
			Cliente cliente = (Cliente) entity;
			// textClienteNome.setText(entityCliente.getNome());
			// textClienteEmail.setText(entityCliente.getEmail());
			bloqueadoGroup.selectToggle(IndicadorSN.SIM.equals(entityCliente.getBloqueado()) ? radioClienteBloqueadoSim
					: radioClienteBloqueadoNao);
			tipoPessoa
					.selectToggle(cliente instanceof ClientePF ? radioClientePessoaFisica : radioClientePessoaJuridica);
			// if (entity instanceof ClientePF) {
			// ClientePF pf = (ClientePF) entityCliente;
			// tipoPessoa.selectToggle(radioClientePessoaFisica);
			// textClienteCpf.setText(pf.getCpf());
			// textClienteCnh.setText(String.valueOf(pf.getCnh()));
			// textClienteRg.setText(pf.getRg());
			// } else {
			// tipoPessoa.selectToggle(radioClientePessoaJuridica);
			// textClienteCpf.setText(((ClientePJ)
			// entityCliente).getCnpj());
			// }
			radioClientePessoaFisica.setDisable(true);
			radioClientePessoaJuridica.setDisable(true);
			textClienteCpf.setDisable(true);
			target = entityCliente;
			if (cliente.getEndereco() != null) {
				cliente.getEndereco().copyProperties(endereco);
			} else {
				new Endereco().copyProperties(endereco);
			}
		} else if (entity instanceof Usuario) {
			target = entityUsuario;
		} else if (entity instanceof Material) {
			target = entityMaterial;
		}
		entity.copyProperties(target);
	}

}
