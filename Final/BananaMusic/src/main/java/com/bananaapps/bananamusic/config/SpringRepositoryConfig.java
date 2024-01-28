package com.bananaapps.bananamusic.config;

import com.bananaapps.bananamusic.persistence.music.SongRepository;
import com.bananaapps.bananamusic.persistence.music.JpaSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@PropertySource("classpath:jdbc.properties")
public class SpringRepositoryConfig {

	/*@Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(bananaEmf().getObject());
        return transactionManager;
    }

    @Bean
    DataSource bananaDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("banana.driverClassName"));
        ds.setUrl(env.getProperty("banana.url"));
        ds.setUsername(env.getProperty("banana.dbUserName"));
        ds.setPassword(env.getProperty("banana.password"));
        return ds;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setShowSql(true);
        va.setGenerateDdl(false);
        return va;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bananaEmf() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(bananaDataSource());
        em.setPersistenceUnitName(env.getProperty("banana.persistenceUnitName"));
        em.setPackagesToScan("com.banana.*");
        em.setJpaVendorAdapter(vendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("banana.dialect"));
        return properties;
    }
*/
	@Bean
	public SongRepository songRepository() {
		return new JpaSongRepository();
	}

}