package com.sigamfe.controller;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import lombok.Getter;

@Controller
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CadastroControllerImpl implements CadastroController {

	private static final long serialVersionUID = -3277976711219254609L;

	private MaskValidator CPF_VALIDATOR = new MaskValidator(MaskValidator.CPF_MASK);
	private MaskValidator CNPJ_VALIDATOR = new MaskValidator(MaskValidator.CNPJ_MASK);
	private MaskValidator CEP_VALIDATOR = new MaskValidator(MaskValidator.CEP_MASK);
	private MaskValidator RG_VALIDATOR = new MaskValidator(MaskValidator.RG_MASK);

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
		labelClienteTipoPessoa.setText(isPessoaFisica() ? "CPF*" : "CNPJ*");
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

	}

	@Override
	@FXML
	public void removeClienteTelefone() {

	}

	@Override
	@FXML
	public void salvaCliente() {
		if (entityCliente == null) {
			entityCliente = isPessoaFisica() ? new ClientePF() : new ClientePJ();
			if (isPessoaFisica()) {
				entityCliente = new ClientePF();
			} else {
				entityCliente = new ClientePJ();
			}
		}
		if (isPessoaFisica()) {
			entityCliente = new ClientePF();
			((ClientePF) entityCliente).setCpf(textClienteCpf.getText());
			((ClientePF) entityCliente).setRg(textClienteRg.getText());
			if (StringUtils.isNotBlank(textClienteCnh.getText())) {
				((ClientePF) entityCliente).setCnh(Long.parseLong(textClienteCnh.getText()));
			}
		} else {
			entityCliente = new ClientePJ();
			((ClientePJ) entityCliente).setCnpj(textClienteCpf.getText());
		}
		Endereco end = new Endereco();
		end.setCidade(textClienteCidade.getText());

		// Atributos opcionais devem ser nulos se deixados em branco, por isso o
		// uso do StringUtils.
		end.setCep(StringUtils.defaultIfBlank(textClienteCep.getText(), null));
		end.setCidade(textClienteCidade.getText());
		end.setLogradouro(textClienteEndereco.getText());
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
		stage.setTitle(Titles.CADASTRO_WINDOW_TITLE);
		stage.setResizable(false);

		// Inicializando o combo de unidade de materiais
		comboMaterialUnidade.getItems().addAll(IndicadorUnidade.values());
		comboMaterialUnidade.setConverter(new FxEnumConverter<>(IndicadorUnidade.class));

		textClienteCpf.textProperty().addListener(new FilteredChangeListener(textClienteCpf) {

			@Override
			protected String getFiltered(String newValue, String oldValue) {
				return TextFieldUtils.processMask(newValue, oldValue,
						isPessoaFisica() ? CPF_VALIDATOR : CNPJ_VALIDATOR);
			}
		});
		textClienteCep.textProperty().addListener(new FilteredChangeListener(textClienteCep) {

			@Override
			protected String getFiltered(String newValue, String oldValue) {
				return TextFieldUtils.processMask(newValue, oldValue, CEP_VALIDATOR);
			}
		});
		textClienteRg.textProperty().addListener(new FilteredChangeListener(textClienteRg) {

			@Override
			protected String getFiltered(String newValue, String oldValue) {
				return TextFieldUtils.processMask(newValue, oldValue, RG_VALIDATOR);
			}
		});

		textMaterialAluguel.textProperty().addListener(new FilteredChangeListener(textMaterialAluguel) {

			@Override
			protected String getFiltered(String newValue, String oldValue) {
				return TextFieldUtils.processMaxDecimal(newValue, oldValue, 1, 6, 2);
			}
		});
		textMaterialReposicao.textProperty().addListener(new FilteredChangeListener(textMaterialReposicao) {

			@Override
			protected String getFiltered(String newValue, String oldValue) {
				return TextFieldUtils.processMaxDecimal(newValue, oldValue, 1, 6, 2);
			}
		});

		stage.showAndWait();
	}

}
