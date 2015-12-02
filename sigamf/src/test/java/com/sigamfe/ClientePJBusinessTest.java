package com.sigamfe;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sigamfe.base.BaseTest;
import com.sigamfe.business.ClientePJBusiness;
import com.sigamfe.model.Cliente;
import com.sigamfe.model.ClientePJ;
import com.sigamfe.model.Endereco;
import com.sigamfe.model.enums.IndicadorSN;

public class ClientePJBusinessTest extends BaseTest {

	@Autowired
	private ClientePJBusiness clientePJBusiness;

	@Test
	public void tes_findAll() {
		Endereco endereco = new Endereco();
		endereco.setCep("11333-000");
		endereco.setCidade("Belo Horizonte");
		endereco.setUf("MG");
		endereco.setLogradouro("Rua Gacuí");
		endereco.setNumero("21");

		Cliente cliente = new ClientePJ();
		cliente.setNome("CEFET-MG");
		cliente.setCp("17.220.203/0001-96");
		cliente.setEmail("cefet@sigamfe.com.br");
		cliente.setEndereco(endereco);
		cliente.setBloqueado(IndicadorSN.NAO);
		cliente.setJaFoiBloqueado(IndicadorSN.NAO);
		clientePJBusiness.save((ClientePJ) cliente);
		Assert.assertTrue(!clientePJBusiness.findAll().isEmpty());
	}
}
