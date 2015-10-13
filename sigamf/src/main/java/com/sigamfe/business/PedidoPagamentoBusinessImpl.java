package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.PedidoPagamento;
import com.sigamfe.model.PedidoPagamento.PedidoPagamentoPK;
import com.sigamfe.repository.PedidoPagamentoRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PedidoPagamentoBusinessImpl extends AbstractBusiness<PedidoPagamentoPK, PedidoPagamento>
		implements PedidoPagamentoBusiness {

	private static final long serialVersionUID = 7197593209756779817L;

	@Getter
	@Autowired
	private PedidoPagamentoRepository repository;
}
