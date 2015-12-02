package com.sigamfe;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.sigamfe.base.BaseTest;
import com.sigamfe.business.ClientePFBusiness;
import com.sigamfe.model.Cliente;
import com.sigamfe.model.ClientePF;
import com.sigamfe.model.Endereco;
import com.sigamfe.model.enums.IndicadorSN;

public class ClientePFBusinessTest extends BaseTest {

	@Autowired
	private ClientePFBusiness clientePFBusiness;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void tes_findAll() {
		Endereco endereco = new Endereco();
		endereco.setCep("11222-000");
		endereco.setCidade("Belo Horizonte");
		endereco.setUf("MG");
		endereco.setLogradouro("Rua Jacuí");
		endereco.setNumero("12");

		Cliente cliente = new ClientePF();
		cliente.setNome("João da Silva");
		cliente.setCp("123456789-22");
		cliente.setEmail("joao@sigamfe.com.br");
		cliente.setEndereco(endereco);
		cliente.setBloqueado(IndicadorSN.NAO);
		cliente.setJaFoiBloqueado(IndicadorSN.NAO);
		clientePFBusiness.save((ClientePF) cliente);
		Assert.assertTrue(!clientePFBusiness.findAll().isEmpty());
	}
}
