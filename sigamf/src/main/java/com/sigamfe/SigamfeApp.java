package com.sigamfe;

import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.stage.Stage;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;

import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.controller.LoginController;
import com.sigamfe.model.Usuario;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.PermissaoUsuario;
import com.sigamfe.repository.UsuarioRepository;

@Lazy
@SpringBootApplication
@EnableAspectJAutoProxy
public class SigamfeApp extends Application {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PooledPBEStringEncryptor pooledPBEStringEncryptor;

	private static ConfigurableApplicationContext applicationContext;

	private static String[] savedArgs;

	@Override
	public void init() throws Exception {
		applicationContext = SpringApplication.run(SigamfeApp.class, savedArgs);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		applicationContext.close();
	}

	public static void launchApp(String[] args) {
		SigamfeApp.savedArgs = args;
		Application.launch(SigamfeApp.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));

		LoginController loginController = applicationContext.getBean(LoginController.class);

		loginController.getDialog().showAndWait();

		if (usuarioRepository.count() == 0) {
			Usuario usuario = new Usuario();
			usuario.setLogin("admSigamfe");
			usuario.setSenhaEncriptando(pooledPBEStringEncryptor, "sigPass");
			usuario.setAtivo(IndicadorSN.SIM);
			usuario.setCpf("014.795.246-89");
			usuario.setDataCriacao(LocalDateTime.now());
			usuario.setTelefone(930040829L);
			usuario.setPermissao(PermissaoUsuario.ADMINISTRADOR);
			usuarioRepository.saveAndFlush(usuario);
		}

	}

	public static void main(String[] args) {

		PersistenceConfiguration.setDB_HOSTNAME("localhost");
		PersistenceConfiguration.setDB_PORT("52000");

		launchApp(args);
	}
}
