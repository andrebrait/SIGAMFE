package com.sigamfe.controller.base;

import java.io.Serializable;

public interface BaseController extends Serializable {

	ViewStage getStage();

	void initializeWindow();

}
