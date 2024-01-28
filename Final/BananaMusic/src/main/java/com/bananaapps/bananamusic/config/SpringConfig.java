package com.bananaapps.bananamusic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement 
@ComponentScan(basePackages = "com.bananaapps.bananamusic")
@Import({SpringRepositoryConfig.class, SpringServicesConfig.class})
@PropertySource("classpath:application.yml")
public class SpringConfig {
	    	
}
