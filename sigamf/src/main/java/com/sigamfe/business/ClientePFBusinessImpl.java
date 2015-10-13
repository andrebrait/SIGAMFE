package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.ClientePF;
import com.sigamfe.repository.ClientePFRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClientePFBusinessImpl extends AbstractBusiness<Integer, ClientePF> implements ClientePFBusiness {

	private static final long serialVersionUID = 224328374334868349L;

	@Getter
	@Autowired
	private ClientePFRepository repository;

}
