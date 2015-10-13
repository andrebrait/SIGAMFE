package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.TelefoneCliente;
import com.sigamfe.model.TelefoneCliente.TelefoneClientePK;
import com.sigamfe.repository.TelefoneClienteRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TelefoneClienteBusinessImpl extends AbstractBusiness<TelefoneClientePK, TelefoneCliente>
		implements TelefoneClienteBusiness {

	private static final long serialVersionUID = -4131416868187540842L;

	@Getter
	@Autowired
	private TelefoneClienteRepository repository;
}
