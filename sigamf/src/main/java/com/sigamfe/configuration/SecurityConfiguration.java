package com.sigamfe.configuration;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.springsecurity3.authentication.encoding.PBEPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfiguration {

	@Bean
	public PooledPBEStringEncryptor encryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		encryptor.setPassword("SIGAMFE_Pass_###71662####$%$%");
		encryptor.setPoolSize(4);
		encryptor.setKeyObtentionIterations(2);
		return encryptor;
	}

	@Bean
	public PBEPasswordEncoder passwordEncoder(PooledPBEStringEncryptor encrytor) {
		PBEPasswordEncoder passwordEncoder = new PBEPasswordEncoder();
		passwordEncoder.setPbeStringEncryptor(encrytor);
		return passwordEncoder;
	}

	@Bean
	public AuthenticationProvider authenticationProvider(PBEPasswordEncoder encoder, UserDetailsService userDetailsService) {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setPasswordEncoder(encoder);
		auth.setUserDetailsService(userDetailsService);
		return auth;
	}

}
