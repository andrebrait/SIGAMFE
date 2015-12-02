package com.sigamfe;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.sigamfe.base.BaseTest;
import com.sigamfe.business.ClientePJBusiness;
import com.sigamfe.model.Cliente;
import com.sigamfe.model.ClientePF;
import com.sigamfe.model.ClientePJ;
import com.sigamfe.model.Endereco;
import com.sigamfe.model.enums.IndicadorSN;

public class ClientePJBusinessTest extends BaseTest {

	@Autowired
	private ClientePJBusiness clientePJBusiness;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void tes_findAll() {
		Endereco endereco = new Endereco();
		endereco.setCep("11333-000");
		endereco.setCidade("Belo Horizonte");
		endereco.setUf("MG");
		endereco.setLogradouro("Rua Gacuí");
		endereco.setNumero("21");

		Cliente cliente = new ClientePJ();
		cliente.setNome("Maria da Silva");
		cliente.setCp("1234567890123456"); // Me deu duvida qual é a estrutura
											// do cnpj
		cliente.setEndereco(endereco);
		cliente.setBloqueado(IndicadorSN.NAO);
		cliente.setJaFoiBloqueado(IndicadorSN.NAO);
		clientePFBusiness.save((ClientePF) cliente);
		Assert.assertTrue(!clientePJBusiness.findAll().isEmpty());
	}
}
