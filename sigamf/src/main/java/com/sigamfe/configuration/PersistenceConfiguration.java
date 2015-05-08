package com.sigamfe.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.mariadb.jdbc.MySQLDataSource;
import org.springframework.context.annotation.Bean;
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

/**
 * The Class PersistenceContext.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.sigamfe.repository")
public class PersistenceConfiguration {

	@Getter
	@Setter
	private static String DB_HOSTNAME, DB_PORT, DB_DATABASE;

	private static final String DB_USERNAME = "sigamfe", DB_PASSWORD = "TEHASi", AUTOCONNECTION_TEST_QUERY = "SELECT 1";
	private static final boolean AUTO_COMMIT = true;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		final DataSource datasource = new MySQLDataSource(DB_HOSTNAME, Integer.parseInt(DB_PORT), DB_DATABASE);
		final HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDataSource(datasource);
		hikariConfig.setUsername(DB_USERNAME);
		hikariConfig.setPassword(DB_PASSWORD);
		hikariConfig.setAutoCommit(AUTO_COMMIT);
		hikariConfig.setConnectionTestQuery(AUTOCONNECTION_TEST_QUERY);

		return new HikariDataSource(hikariConfig);
	}

	private static final String PACKAGES_TO_SCAN = "com.sigamfe.model", HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL5InnoDBDialect",
			HIBERNATE_EJB_NAMING_STRATEGY = "org.hibernate.cfg.ImprovedNamingStrategy", HIBERNATE_SHOW_SQL = "false", HIBERNATE_FORMAT_SQL = "true",
			HIBERNATE_HBM2DDL_AUTO = "validate";

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
		final Properties properties = new Properties();
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
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public HibernatePBEStringEncryptor hibernatePBEStringEncryptor() {
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
