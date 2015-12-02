package com.sigamfe;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.sigamfe.base.BaseTest;
import com.sigamfe.business.MaterialBusiness;
import com.sigamfe.model.Material;
import com.sigamfe.model.enums.IndicadorUnidade;

public class MaterialBusinessTest extends BaseTest {

	@Autowired
	private MaterialBusiness materialBusiness;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void test_findAll() {
		Material material = new Material();
		material.setCodigo(1);
		material.setDescricao("Mesa");
		material.setValorAluguel(BigDecimal.valueOf(25.50));
		material.setValorReposicao(BigDecimal.valueOf(27.50));
		material.setUnidade(IndicadorUnidade.UNIDADE);
		materialBusiness.save(material);
		Assert.assertTrue(!materialBusiness.findAll().isEmpty());

	}
}
