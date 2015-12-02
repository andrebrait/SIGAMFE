package com.sigamfe;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sigamfe.base.BaseTest;
import com.sigamfe.business.MaterialBusiness;
import com.sigamfe.model.Material;
import com.sigamfe.model.enums.IndicadorUnidade;

public class MaterialBusinessTest extends BaseTest {

	@Autowired
	private MaterialBusiness materialBusiness;

	@Test
	public void test_findAll() {
		Material material = new Material();
		material.setCodigo(1);
		material.setDescricao("Mesa");
		material.setValorAluguel(new BigDecimal("25.50"));
		material.setValorReposicao(new BigDecimal("27.50"));
		material.setUnidade(IndicadorUnidade.UNIDADE);
		materialBusiness.save(material);
		Assert.assertTrue(!materialBusiness.findAll().isEmpty());

	}
}
