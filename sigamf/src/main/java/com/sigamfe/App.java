package com.sigamfe;

import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

	@Autowired
	public static HibernatePBEStringEncryptor usuarioRepository;

	public static void main(String[] args) {

		final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/application-context.xml");

		System.out.println("Hello World!");
		System.out.println(usuarioRepository);
	}
}
