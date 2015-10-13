package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.System;
import com.sigamfe.repository.SystemRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemBusinessImpl extends AbstractBusiness<Byte, System> implements SystemBusiness {

	private static final long serialVersionUID = -1646362890328777748L;

	@Getter
	@Autowired
	private SystemRepository repository;
}
