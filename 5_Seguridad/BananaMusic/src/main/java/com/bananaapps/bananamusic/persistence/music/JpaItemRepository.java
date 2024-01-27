/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.bananaapps.bananamusic.persistence.music;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bananaapps.bananamusic.domain.music.MusicCategory;
import com.bananaapps.bananamusic.domain.music.MusicItem;

public class JpaItemRepository implements ItemRepository {

	@PersistenceContext
	private EntityManager em;

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public MusicItem findOne(Long id) {
		MusicItem item = em.find(MusicItem.class, id);
		return item;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Collection<MusicItem> findAll() {
		// Not implemented
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Collection<MusicItem> findByKeyword(String keyword) {
		// Not implemented
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.NEVER)
	public long count() {
		// Not implemented
		return 0;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Collection<MusicItem> findByArtistContainingOrTitleContainingAllIgnoreCase(String artist, String title) {
		// Not implmented
		return null;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Collection<MusicItem> findByMusicCategory(MusicCategory category) {
		// Not implemented
		return null;
	}
	
	@Override
	// DONE: Change TX attribute to REQUIRES_NEW
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public MusicItem save(MusicItem item) {
		em.persist(item);
		return item;
	}

	@Override
	public void delete(MusicItem item) {
		em.remove(item);
		
	}
}
