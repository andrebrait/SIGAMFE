package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.ClientePJ;
import com.sigamfe.repository.ClientePJRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClientePJBusinessImpl extends AbstractBusiness<Integer, ClientePJ> implements ClientePJBusiness {

	private static final long serialVersionUID = -8736679327188728842L;

	@Getter
	@Autowired
	private ClientePJRepository repository;
}
