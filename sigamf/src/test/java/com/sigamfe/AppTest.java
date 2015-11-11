package com.sigamfe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sigamfe.business.UsuarioBusiness;

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@SpringBootApplication
@EnableLoadTimeWeaving
@EnableSpringConfigured
public class AppTest {

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@Test
	public void test_findAll() {
		Assert.assertNotNull(usuarioBusiness.findAll());
	}
}
