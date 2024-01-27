package com.bananaapps.bananamusic.config;


import com.bananaapps.bananamusic.persistence.music.ItemRepository;
import com.bananaapps.bananamusic.service.music.Catalog;
import com.bananaapps.bananamusic.service.music.CatalogImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


//@Configuration
public class SpringServicesConfig {
	
	@Autowired
	ItemRepository repository;
	
	@Bean
	public Catalog catalog() {
		CatalogImpl cat = new CatalogImpl();
		cat.setItemRepository(repository);
		return cat;
	}

}