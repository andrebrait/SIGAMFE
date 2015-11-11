package com.sigamfe.configuration;

import org.springframework.context.annotation.Configuration;

import com.sigamfe.controller.MainWindowController;
import com.sigamfe.model.Usuario;

@Configuration
public class ApplicationConfiguration {

	public static Usuario usuarioLogado;

	public static MainWindowController mainWindowController;

}
