package com.sigamfe.configuration;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {

	@Bean
	public HibernatePBEStringEncryptor hibernateStringEncryptor() {
		final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		encryptor.setPassword("SIGAMFE_Pass_###71662####$%$%");
		encryptor.setPoolSize(4);
		encryptor.setKeyObtentionIterations(2);
		final HibernatePBEStringEncryptor hibEncryptor = new HibernatePBEStringEncryptor();
		hibEncryptor.setRegisteredName("strongHibernateStringEncryptor");
		hibEncryptor.setEncryptor(encryptor);
		return hibEncryptor;
	}
}
