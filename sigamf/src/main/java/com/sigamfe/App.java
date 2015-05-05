package com.sigamfe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sigamfe.repository.UsuarioRepository;

/**
 * Hello world!
 *
 */
public class App {

	@Autowired
	public static UsuarioRepository usuarioRepository;

	public static void main(String[] args) throws Exception {

		final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/application-context.xml");

		System.out.println("Hello World!");
		System.out.println(usuarioRepository);

		throw new Exception();
	}
}
