package com.sigamfe.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.ClientePFBusiness;
import com.sigamfe.business.ClientePJBusiness;
import com.sigamfe.business.ImagemMaterialBusiness;
import com.sigamfe.business.MaterialBusiness;
import com.sigamfe.business.UsuarioBusiness;
import com.sigamfe.configuration.constants.Labels;
import com.sigamfe.configuration.constants.Messages;
import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.controller.base.BaseController;
import com.sigamfe.model.Cliente;
import com.sigamfe.model.ClientePF;
import com.sigamfe.model.ClientePJ;
import com.sigamfe.model.Endereco;
import com.sigamfe.model.ImagemMaterial;
import com.sigamfe.model.Material;
import com.sigamfe.model.TelefoneCliente;
import com.sigamfe.model.Usuario;
import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.IndicadorUnidade;
import com.sigamfe.model.enums.PermissaoUsuario;
import com.sigamfe.model.enums.converter.javafx.FxBigDecimalConverter;
import com.sigamfe.model.enums.converter.javafx.FxEnumConverter;
import com.sigamfe.model.enums.converter.javafx.FxIntegerConverter;
import com.sigamfe.util.FilteredChangeListener;
import com.sigamfe.util.ImageUtils;
import com.sigamfe.util.MaskValidator;
import com.sigamfe.util.TelefoneUtils.TelefoneConverter;
import com.sigamfe.util.TextFieldUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CadastroController implements BaseController {

	private static final long serialVersionUID = -3277976711219254609L;

	private Cliente entityCliente;

	private Endereco endereco;

	private Material entityMaterial;

	private Usuario entityUsuario;

	@Autowired
	private ClientePFBusiness clientePFBusiness;

	@Autowired
	private ClientePJBusiness clientePJBusiness;

	@Autowired
	private MaterialBusiness materialBusiness;

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@Autowired
	private ImagemMaterialBusiness imagemMaterialBusiness;

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

	@Override
	public void initializeWindow() {
		initCliente();
		initMaterial();
		initUsuario();
	}

	private void initCliente() {
		entityCliente = new Cliente() {

			private static final long serialVersionUID = -1L;

		};
		endereco = new Endereco();
		tipoPessoa.selectedToggleProperty().addListener((obs, oldValue, newValue) -> {
			boolean pj = newValue.equals(radioClientePessoaJuridica);
			textClienteRg.setDisable(pj);
			textClienteCnh.setDisable(pj);
			// O Espaço no final serve para forçar o refresh do campo
			textClienteCpf.setText(textClienteCpf.getText() + " ");
			buttonPesquisaClienteRg.setDisable(pj);
			if (pj) {
				textClienteRg.clear();
				textClienteCnh.clear();
				labelClienteTipoPessoa.setText(Labels.CNPJ + Labels.OBRIGATORIO);
			} else {
				labelClienteTipoPessoa.setText(Labels.CPF + Labels.OBRIGATORIO);
			}
		});

		bloqueadoGroup.selectedToggleProperty().addListener((obs, oldValue, newValue) -> entityCliente
				.setBloqueado(newValue.equals(radioClienteBloqueadoSim) ? IndicadorSN.SIM : IndicadorSN.NAO));

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
		textClienteCpf.textProperty().bindBidirectional(retrieveProperty(entityCliente, "cp"));

		textClienteCep.textProperty().addListener(new FilteredChangeListener(textClienteCep,
				(newValue, oldValue) -> TextFieldUtils.processMask(newValue, oldValue, MaskValidator.CEP_VALIDATOR)));
		textClienteCep.textProperty().bindBidirectional(retrieveProperty(endereco, "cep"));

		textClienteRg.textProperty()
				.addListener(new FilteredChangeListener(textClienteRg,
						(newValue, oldValue) -> MaskValidator.getVersionByInsertedChar(newValue, oldValue,
								MaskValidator.RG_VALIDATOR_1_LETRA, MaskValidator.RG_VALIDATOR_2_LETRAS)));
		textClienteRg.textProperty().bindBidirectional(retrieveProperty(entityCliente, "rg"));

		textClienteCnh.textProperty().addListener(new FilteredChangeListener(textClienteCnh,
				(newValue, oldValue) -> TextFieldUtils.processMask(newValue, oldValue, MaskValidator.CNH_VALIDATOR)));
		textClienteCnh.textProperty().bindBidirectional(retrieveProperty(entityCliente, "cnh"));

		textClienteNome.textProperty().addListener(new FilteredChangeListener(textClienteNome,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 100)));
		textClienteNome.textProperty().bindBidirectional(retrieveProperty(entityCliente, "nome"));

		textClienteEmail.textProperty().addListener(new FilteredChangeListener(textClienteEmail,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 200)));
		textClienteEmail.textProperty().bindBidirectional(retrieveProperty(entityCliente, "email"));

		textClienteCidade.textProperty().addListener(new FilteredChangeListener(textClienteCidade,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 200)));
		textClienteCidade.textProperty().bindBidirectional(retrieveProperty(endereco, "cidade"));

		textClienteUf.textProperty().addListener(new FilteredChangeListener(textClienteUf,
				(newValue, oldValue) -> TextFieldUtils.processMask(newValue, oldValue, MaskValidator.UF_VALIDATOR)));
		textClienteUf.textProperty().bindBidirectional(retrieveProperty(endereco, "uf"));

		textClienteNumero.textProperty().addListener(new FilteredChangeListener(textClienteNumero,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 30)));
		textClienteNumero.textProperty().bindBidirectional(retrieveProperty(endereco, "numero"));

		textClienteEndereco.textProperty().addListener(new FilteredChangeListener(textClienteEndereco,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 200)));
		textClienteEndereco.textProperty().bindBidirectional(retrieveProperty(endereco, "logradouro"));
	}

	private void resetScreenCliente() {
		new Cliente() {

			private static final long serialVersionUID = -1L;

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
		Cliente buscado;
		if (isPessoaFisica()) {
			buscado = clientePFBusiness.findByNome(entityCliente.getNome());
		} else {
			buscado = clientePJBusiness.findByNome(entityCliente.getNome());
		}
		if (buscado == null) {
			showError("Erro", "Cliente não encontrado!");
		} else {
			showInformation("Sucesso", "Cliente encontrado com sucesso!");
			loadEntity(buscado);
		}
	}

	@FXML
	public void pesquisaClienteCpfCnpj() {
		Cliente buscado;
		if (isPessoaFisica()) {
			buscado = clientePFBusiness.findByCpf(entityCliente.getCp());
		} else {
			buscado = clientePJBusiness.findByCnpj(entityCliente.getCp());
		}
		if (buscado == null) {
			showError("Erro", "Cliente não encontrado!");
		} else {
			showInformation("Sucesso", "Cliente encontrado com sucesso!");
			loadEntity(buscado);
		}
	}

	@FXML
	public void pesquisaClienteRg() {
		ClientePF buscado = clientePFBusiness.findByRg(entityCliente.getRg());
		if (buscado == null) {
			showError("Erro", "Cliente não encontrado!");
		} else {
			showInformation("Sucesso", "Cliente encontrado com sucesso!");
			loadEntity(buscado);
		}
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
		dialogNovoTelefone.showAndWait().ifPresent((str) -> {
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
		Endereco end = new Endereco();
		endereco.copyProperties(end);
		toSave.setEndereco(end);
		toSave.setTelefones(tableClienteTelefones.getItems());

		if (toSave instanceof ClientePF) {
			clientePFBusiness.save((ClientePF) toSave);
		} else {
			clientePJBusiness.save((ClientePJ) toSave);
		}

		showInformation("Sucesso", "Cliente cadastrado com sucesso!");
		toSave.copyProperties(entityCliente);
		toSave.getEndereco().copyProperties(endereco);
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
				resetScreenCliente();
			}
		} else {
			resetScreenCliente();
		}
	}

	@FXML
	public void cancelar() {
		getParentStage(textClienteCpf).close();
	}

	/*
	 * Aqui começa a parte do controller dedicada à tela de material.
	 */

	@FXML
	private StackPane paneMaterialImagem;

	private Pane paneMaterialShowImagem;

	private Pane paneMaterialSelectImagem;

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
		imagemMaterial.setImage(null);
		entityMaterial.setImagem(null);
		setStackOrder(paneMaterialSelectImagem, paneMaterialShowImagem);
	}

	@FXML
	public void adicionarImagemMaterial() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir imagem");
		fileChooser.getExtensionFilters()
				.add(new ExtensionFilter("Imagens", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp"));
		File selectedFile = fileChooser.showOpenDialog(getParentStage(imagemMaterial));
		if (selectedFile != null) {
			imagemMaterial.setImage(new Image("file:" + selectedFile.getAbsolutePath()));
			entityMaterial.setImagem(new ImagemMaterial());
			entityMaterial.getImagem().setMaterial(entityMaterial);
			entityMaterial.getImagem().setImagem(ImageUtils.convertImageToBytes(imagemMaterial.getImage()));
			setStackOrder(paneMaterialShowImagem, paneMaterialSelectImagem);
		}
	}

	@FXML
	public void buscaMaterialCodigo() {
		Material buscado = materialBusiness.findByCodigo(entityMaterial.getCodigo());
		if (buscado == null) {
			showError("Erro", "Material não encontrado!");
		} else {
			showInformation("Sucesso", "Material encontrado com sucesso!");
			loadEntity(buscado);
		}
	}

	@FXML
	public void buscaMaterialDescricao() {
		Material buscado = materialBusiness.findByDescricao(entityMaterial.getDescricao());
		if (buscado == null) {
			showError("Erro", "Material não encontrado!");
		} else {
			showInformation("Sucesso", "Material encontrado com sucesso!");
			loadEntity(buscado);
		}
	}

	@FXML
	public void salvarMaterial() {
		Material toSave = new Material();
		entityMaterial.copyProperties(toSave);
		materialBusiness.save(toSave);
		showInformation("Sucesso", "Material cadastrado com sucesso!");
		if (showConfirmation("Adicionar novo", "Deseja adicionar outro material?")) {
			resetScreenMaterial();
		} else {
			loadEntity(toSave);
		}
	}

	@FXML
	public void excluirMaterial() {
		if (entityMaterial.getId() != null) {
			if (showConfirmation("Excluir material", Messages.CONFIRM_EXCLUSAO)) {
				materialBusiness.delete(entityMaterial.getId());
				resetScreenMaterial();
			}
		} else {
			resetScreenMaterial();
		}
	}

	private void initMaterial() {
		entityMaterial = new Material();

		textMaterialCodigo.textProperty().addListener(new FilteredChangeListener(textMaterialCodigo,
				(newValue, oldValue) -> TextFieldUtils.processMaxDecimal(newValue, oldValue, 0, 4, 0)));
		textMaterialCodigo.textProperty().bindBidirectional(retrieveProperty(entityMaterial, "codigo"),
				new FxIntegerConverter());

		textMaterialDescricao.textProperty().addListener(new FilteredChangeListener(textMaterialDescricao,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 200)));
		textMaterialDescricao.textProperty().bindBidirectional(retrieveProperty(entityMaterial, "descricao"));

		textMaterialAluguel.textProperty().addListener(new FilteredChangeListener(textMaterialAluguel,
				(newValue, oldValue) -> TextFieldUtils.processMaxDecimal(newValue, oldValue, 1, 6, 2)));
		textMaterialAluguel.textProperty().bindBidirectional(retrieveProperty(entityMaterial, "valorAluguel"),
				new FxBigDecimalConverter());

		textMaterialReposicao.textProperty().addListener(new FilteredChangeListener(textMaterialReposicao,
				(newValue, oldValue) -> TextFieldUtils.processMaxDecimal(newValue, oldValue, 1, 6, 2)));
		textMaterialReposicao.textProperty().bindBidirectional(retrieveProperty(entityMaterial, "valorReposicao"),
				new FxBigDecimalConverter());

		// Inicializando o combo de unidade de materiais
		comboMaterialUnidade.getItems().addAll(IndicadorUnidade.values());
		comboMaterialUnidade.setConverter(new FxEnumConverter<>(IndicadorUnidade.class));
		comboMaterialUnidade.valueProperty().bindBidirectional(retrieveProperty(entityMaterial, "unidade"));

		imagemMaterial.setPreserveRatio(true);
		imagemMaterial.setFitHeight(220);
		imagemMaterial.setFitHeight(220);

		paneMaterialSelectImagem = (Pane) paneMaterialImagem.getChildren().get(0);
		paneMaterialShowImagem = (Pane) paneMaterialImagem.getChildren().get(1);

	}

	private void setStackOrder(Node first, Node second) {
		ObservableList<Node> workingCollection = FXCollections.observableArrayList(first, second);
		paneMaterialImagem.getChildren().setAll(workingCollection);
	}

	private void resetScreenMaterial() {
		new Material().copyProperties(entityMaterial);
		textMaterialCodigo.setDisable(false);
		imagemMaterial.setImage(null);
		setStackOrder(paneMaterialSelectImagem, paneMaterialShowImagem);
	}

	/*
	 * Usuário
	 */

	@FXML
	private TextField textUsuarioCpf;

	@FXML
	private TextField textUsuarioLogin;

	@FXML
	private ComboBox<PermissaoUsuario> comboUsuarioPermissao;

	@FXML
	private TextField textUsuarioTelefone;

	@FXML
	public void pesquisaUsuarioLogin() {
		Usuario buscado = usuarioBusiness.findByLogin(entityUsuario.getLogin());
		if (buscado == null) {
			showError("Erro", "Usuário não encontrado!");
		} else {
			showInformation("Sucesso", "Usuário encontrado com sucesso!");
			loadEntity(buscado);
		}
	}

	@FXML
	public void pesquisaUsuarioCpf() {
		Usuario buscado = usuarioBusiness.findByCpf(entityUsuario.getCpf());
		if (buscado == null) {
			showError("Erro", "Usuário não encontrado!");
		} else {
			showInformation("Sucesso", "Usuário encontrado com sucesso!");
			loadEntity(buscado);
		}
	}

	private void initUsuario() {
		entityUsuario = new Usuario();

		textUsuarioCpf.textProperty().addListener(new FilteredChangeListener(textUsuarioCpf,
				(newValue, oldValue) -> MaskValidator.CPF_VALIDATOR.validate(newValue, oldValue)));
		textUsuarioCpf.textProperty().bindBidirectional(retrieveProperty(entityUsuario, "cpf"));

		textUsuarioLogin.textProperty().addListener(new FilteredChangeListener(textUsuarioCpf,
				(newValue, oldValue) -> TextFieldUtils.processMaxChars(newValue, 20)));
		textUsuarioLogin.textProperty().bindBidirectional(retrieveProperty(entityUsuario, "login"));

		comboUsuarioPermissao.getItems().addAll(PermissaoUsuario.values());
		comboUsuarioPermissao.setConverter(new FxEnumConverter<>(PermissaoUsuario.class));
		comboUsuarioPermissao.valueProperty().bindBidirectional(retrieveProperty(entityUsuario, "permissao"));

		textUsuarioTelefone.textProperty()
				.addListener(new FilteredChangeListener(textUsuarioTelefone,
						(newValue, oldValue) -> MaskValidator.getVersionByLength(newValue, oldValue,
								MaskValidator.TELEFONE_8_VALIDATOR, MaskValidator.TELEFONE_9_VALIDATOR)));
		textUsuarioTelefone.textProperty().bindBidirectional(retrieveProperty(entityUsuario, "telefone"),
				new TelefoneConverter());
	}

	@FXML
	public void excluirUsuario() {
		if (entityUsuario.getId() != null) {
			if (showConfirmation("Excluir usuário", Messages.CONFIRM_EXCLUSAO)) {
				usuarioBusiness.delete(entityUsuario.getId());
				resetScreenUsuario();
			}
		} else {
			resetScreenUsuario();
		}
	}

	private void resetScreenUsuario() {
		new Usuario().copyProperties(entityUsuario);
		textUsuarioLogin.setDisable(false);
		textUsuarioCpf.setDisable(false);
	}

	@FXML
	public void salvarUsuario() {
		Usuario toSave = new Usuario();
		entityUsuario.copyProperties(toSave);
		if (toSave.getId() == null) {
			toSave.setSenha("12345678");
		}
		usuarioBusiness.save(toSave);
		showInformation("Sucesso", "Usuario cadastrado com sucesso!");
		if (showConfirmation("Adicionar novo", "Deseja adicionar outro usuário?")) {
			resetScreenUsuario();
		} else {
			loadEntity(toSave);
		}
	}

	@Override
	public void loadEntity(BaseEntity<?> entity) {
		BaseEntity<?> target = null;
		if (entity instanceof Cliente) {
			target = entityCliente;
			Cliente cliente = (Cliente) entity;
			cliente.copyProperties(entityCliente);
			bloqueadoGroup.selectToggle(IndicadorSN.SIM.equals(entityCliente.getBloqueado()) ? radioClienteBloqueadoSim
					: radioClienteBloqueadoNao);
			tipoPessoa
					.selectToggle(cliente instanceof ClientePF ? radioClientePessoaFisica : radioClientePessoaJuridica);
			radioClientePessoaFisica.setDisable(true);
			radioClientePessoaJuridica.setDisable(true);
			textClienteCpf.setDisable(true);
			if (cliente.getEndereco() != null) {
				cliente.getEndereco().copyProperties(endereco);
			} else {
				new Endereco().copyProperties(endereco);
			}
		} else if (entity instanceof Usuario) {
			target = entityUsuario;
			textUsuarioLogin.setDisable(true);
			textUsuarioCpf.setDisable(true);
			entity.copyProperties(target);
		} else if (entity instanceof Material) {
			target = entityMaterial;
			textMaterialCodigo.setDisable(true);
			entity.copyProperties(target);
			ImagemMaterial imagem = imagemMaterialBusiness.findById(entityMaterial.getId());
			entityMaterial.setImagem(imagem);
			if (entityMaterial.getImagem() != null) {
				imagemMaterial.setImage(ImageUtils.convertBytesToImage(entityMaterial.getImagem().getImagem()));
				setStackOrder(paneMaterialShowImagem, paneMaterialSelectImagem);
			}
		}
	}

}
