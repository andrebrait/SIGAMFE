package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.TelefoneFornecedor;
import com.sigamfe.model.TelefoneFornecedor.TelefoneFornecedorPK;
import com.sigamfe.repository.TelefoneFornecedorRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TelefoneFornecedorBusinessImpl extends AbstractBusiness<TelefoneFornecedorPK, TelefoneFornecedor>
		implements TelefoneFornecedorBusiness {

	private static final long serialVersionUID = 1486316906373471953L;

	@Getter
	@Autowired
	private TelefoneFornecedorRepository repository;
}
