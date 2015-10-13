package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.MovimentoEstoque;
import com.sigamfe.repository.MovimentoEstoqueRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MovimentoEstoqueBusinessImpl extends AbstractBusiness<Long, MovimentoEstoque>
		implements MovimentoEstoqueBusiness {

	private static final long serialVersionUID = -3314960426304511859L;

	@Getter
	@Autowired
	private MovimentoEstoqueRepository repository;
}
