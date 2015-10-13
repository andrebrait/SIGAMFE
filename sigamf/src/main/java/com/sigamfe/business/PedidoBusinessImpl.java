package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.Pedido;
import com.sigamfe.repository.PedidoRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PedidoBusinessImpl extends AbstractBusiness<Integer, Pedido> implements PedidoBusiness {

	private static final long serialVersionUID = -7228274993000211722L;

	@Getter
	@Autowired
	private PedidoRepository repository;
}
