package com.sigamfe.controller.base;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public interface BaseController extends Serializable {

	ViewStage getStage();

	@PostConstruct
	void initializeWindow();

}
