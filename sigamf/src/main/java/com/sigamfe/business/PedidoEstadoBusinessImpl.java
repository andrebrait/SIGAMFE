package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.PedidoEstado;
import com.sigamfe.repository.PedidoEstadoRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PedidoEstadoBusinessImpl extends AbstractBusiness<Integer, PedidoEstado> implements PedidoEstadoBusiness {

	private static final long serialVersionUID = -7549664716850404281L;

	@Getter
	@Autowired
	private PedidoEstadoRepository repository;
}
