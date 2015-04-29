package com.sigamfe.configuration.springdata.jpa.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeans {

	@Bean
	private PooledPBEStringEncryptor strongEncryptor() {
		final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		encryptor.setPassword("SIGAMFE_Pass_###71662####$%$%");
		encryptor.setPoolSize(4);
		return new PooledPBEStringEncryptor();
	}

	@Bean
	public HibernatePBEStringEncryptor hibernateStringEncryptor() {
		final HibernatePBEStringEncryptor hibEncryptor = new HibernatePBEStringEncryptor();
		hibEncryptor.setRegisteredName("strongHibernateStringEncryptor");
		hibEncryptor.setEncryptor(strongEncryptor());
		return hibEncryptor;

	}

}
