package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.FornecedorMaterial;
import com.sigamfe.model.FornecedorMaterial.FornecedorMaterialPK;
import com.sigamfe.repository.FornecedorMaterialRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FornecedorMaterialBusinessImpl extends AbstractBusiness<FornecedorMaterialPK, FornecedorMaterial>
		implements FornecedorMaterialBusiness {

	private static final long serialVersionUID = 1310166326981918884L;

	@Getter
	@Autowired
	private FornecedorMaterialRepository repository;
}
