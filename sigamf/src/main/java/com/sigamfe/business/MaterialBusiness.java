package com.sigamfe.business;

import com.sigamfe.business.base.BaseBusiness;
import com.sigamfe.model.Material;

public interface MaterialBusiness extends BaseBusiness<Integer, Material> {

	Material findByCodigo(Integer codigo);

	Material findByDescricao(String codigo);

}
