package com.sigamfe.controller.base;

import java.io.Serializable;

public interface BaseController extends Serializable {

	/**
	 * Retorna o stage que controla a janela.
	 *
	 * @return the stage
	 */
	ViewStage getStage();

	/**
	 * Método de inicialização da janela, contendo os comandos necessários para
	 * instanciar a janela apropriadamente. Deve ser anotado com
	 * {@code @PostConstruct}
	 */
	void initializeWindow();

}
