package com.sigamfe;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = "com.sigamfe")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.sigamfe.repository")
public class TestConfiguration {

	private static boolean AUTO_COMMIT = true;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("org.h2.Driver");
		hikariConfig.setUsername("sa");
		hikariConfig.setJdbcUrl("jdbc:h2:mem:datajpa;DB_CLOSE_ON_EXIT=FALSE");
		hikariConfig.setAutoCommit(AUTO_COMMIT);
		return new HikariDataSource(hikariConfig);
	}

	private static String PACKAGES_TO_SCAN = "com.sigamfe.model", HIBERNATE_DIALECT = "org.hibernate.dialect.H2Dialect",
			HIBERNATE_EJB_NAMING_STRATEGY = "org.hibernate.cfg.ImprovedNamingStrategy", HIBERNATE_SHOW_SQL = "false",
			HIBERNATE_FORMAT_SQL = "false", HIBERNATE_HBM2DDL_AUTO = "create-drop";

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
		properties.setProperty("hibernate.ejb.naming_strategy", HIBERNATE_EJB_NAMING_STRATEGY);
		properties.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		properties.setProperty("hibernate.format_sql", HIBERNATE_FORMAT_SQL);
		properties.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
		entityManagerFactoryBean.setJpaProperties(properties);

		return entityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
