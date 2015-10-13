package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.Fornecedor;
import com.sigamfe.repository.FornecedorRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FornecedorBusinessImpl extends AbstractBusiness<Integer, Fornecedor> implements FornecedorBusiness {

	private static final long serialVersionUID = -6733144540219959612L;

	@Getter
	@Autowired
	private FornecedorRepository repository;
}
