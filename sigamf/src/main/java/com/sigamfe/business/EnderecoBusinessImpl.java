package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.Endereco;
import com.sigamfe.repository.EnderecoRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EnderecoBusinessImpl extends AbstractBusiness<Long, Endereco> implements EnderecoBusiness {

	private static final long serialVersionUID = 224328374334868349L;

	@Getter
	@Autowired
	private EnderecoRepository repository;

}
