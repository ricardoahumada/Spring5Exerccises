/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.bananaapps.bananamusic.service.music;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bananaapps.bananamusic.domain.music.MusicItem;
import com.bananaapps.bananamusic.persistence.music.ItemRepository;

public class CatalogImpl implements Catalog{

	@Autowired
	private ItemRepository itemRepository;

	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public MusicItem findById(Long id) {
		return itemRepository.findOne(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Collection<MusicItem> findByKeyword(String keyword) {
		return itemRepository.findByKeyword(keyword);
	}

	@Override
	@Transactional(propagation=Propagation.NEVER)	
	public long size() {
		return itemRepository.count();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public void save(MusicItem item) {
		itemRepository.save(item);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveBatch(Collection<MusicItem> items) {
		for (MusicItem musicItem : items) {
			System.out.println("Attempting to save " + musicItem);
			itemRepository.save(musicItem);	
		}
		System.out.println("If you are seeing this, saveBatch ended normally!");
		
	}
	
	@Override
	public String toString() {
		return "I am a shiny new " + getClass().getName() + " brought to you from Spring" + " but you can just call me " + getClass().getInterfaces()[0] + ".  My itemRepository is " + itemRepository;
	}

}
