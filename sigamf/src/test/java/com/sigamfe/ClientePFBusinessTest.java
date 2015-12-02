package com.sigamfe;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sigamfe.base.BaseTest;
import com.sigamfe.business.ClientePFBusiness;
import com.sigamfe.model.ClientePF;
import com.sigamfe.model.Endereco;
import com.sigamfe.model.enums.IndicadorSN;

public class ClientePFBusinessTest extends BaseTest {

	@Autowired
	private ClientePFBusiness clientePFBusiness;

	@Test
	public void tes_findAll() {
		Endereco endereco = new Endereco();
		endereco.setCep("11222-000");
		endereco.setCidade("Belo Horizonte");
		endereco.setUf("MG");
		endereco.setLogradouro("Rua Jacuí");
		endereco.setNumero("12");

		ClientePF cliente = new ClientePF();
		cliente.setNome("João da Silva");
		cliente.setCpf("015.338.906-09");
		cliente.setEmail("joao@sigamfe.com.br");
		cliente.setEndereco(endereco);
		cliente.setBloqueado(IndicadorSN.NAO);
		cliente.setJaFoiBloqueado(IndicadorSN.NAO);
		clientePFBusiness.save(cliente);
		Assert.assertTrue(!clientePFBusiness.findAll().isEmpty());
	}
}
