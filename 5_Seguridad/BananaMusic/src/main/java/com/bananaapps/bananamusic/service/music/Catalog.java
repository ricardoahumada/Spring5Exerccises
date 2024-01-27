/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.bananaapps.bananamusic.service.music;

import java.util.Collection;

import com.bananaapps.bananamusic.domain.music.MusicItem;

public interface Catalog {

	   public MusicItem findById(Long id);
	   public Collection<MusicItem> findByKeyword(String keyword);
	   public long size();
	   public void save(MusicItem item);
	   public void saveBatch(Collection<MusicItem> items);
	}