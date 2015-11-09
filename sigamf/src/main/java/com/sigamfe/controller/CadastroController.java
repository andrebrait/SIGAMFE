package com.sigamfe.controller;

import com.sigamfe.controller.base.BaseController;

public interface CadastroController extends BaseController {

	void onChangeTogglePessoaCliente();

	void pesquisaClienteCpfCnpj();

	void pesquisaClienteNome();

	void pesquisaClienteRg();

	void pesquisaClienteEnderecoCep();

	void adicionaClienteTelefone();

	void removeClienteTelefone();

	void cancelar();

	void excluiCliente();

	void salvaCliente();

	void removeImagemMaterial();

	void adicionarImagemMaterial();

	void buscaMaterialCodigo();

	void buscaMaterialDescricao();

	void salvarMaterial();

	void excluirMaterial();

	void pesquisaUsuarioLogin();

	void pesquisaUsuarioCPF();

	void pesquisaUsuarioTelefone();

	void pesquisaUsuarioPermissao();

	void excluirUsuario();

	void salvaUsuario();

}
