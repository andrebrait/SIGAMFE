package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.PedidoMaterial;
import com.sigamfe.model.PedidoMaterial.PedidoMaterialPK;
import com.sigamfe.repository.PedidoMaterialRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PedidoMaterialBusinessImpl extends AbstractBusiness<PedidoMaterialPK, PedidoMaterial>
		implements PedidoMaterialBusiness {

	private static final long serialVersionUID = 8255954481894378435L;

	@Getter
	@Autowired
	private PedidoMaterialRepository repository;
}
