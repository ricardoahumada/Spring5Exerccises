package com.bananaapps.bananamusic.service;


import com.bananaapps.bananamusic.domain.music.MusicCategory;
import com.bananaapps.bananamusic.domain.music.MusicItem;
import com.bananaapps.bananamusic.service.music.Catalog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CatalogTest {

    @Autowired
	Catalog cat;
    
	// One TX, everything works properly
	@Test
	public void testFind_WithRequiredTX_Positive() {
		MusicItem item = cat.findById(1L);
		assertNotNull(item);
		System.out.println("Found item" + item);
	}
	
	// Save a null item - should throw an exception when saving.
	@Test
	public void testSave_withNullItem_negative() {
		String expectedExceptionEndString = "event with null entity";
		try {
			cat.save(null);
			fail("Exception not thrown");
		}
		catch (IllegalArgumentException iae) {
			assertTrue(iae.getMessage().endsWith(expectedExceptionEndString));
		}
	}
	
	// Non-transactional method called - no TX
	@Test
	public void testSize_noTX_positive() {
		System.out.println(cat.size());
	}
	
	// Batch persistence done with one null item that blows up when persisted
	// Exception thrown, complete TX rolled back with original TX settings.
	@Test
	public void testSaveBatch_withNullEntity_negative() {  
		String expectedExceptionEndString = "event with null entity";
		try {
			cat.saveBatch(grabBatch());
			fail("Exception not thrown");
		}
		catch (IllegalArgumentException iae) {
			assertTrue(iae.getMessage().endsWith(expectedExceptionEndString));
		}
	}
	
	@Test
	public void testSave_WithRequiredTX_positive() {	
		MusicItem saveItem = new MusicItem("SaveItemTitle", "SaveArtist", "2013-01-04",new BigDecimal("13.99"), MusicCategory.CLASSICAL);
		assertNull(saveItem.getId());
		cat.save(saveItem);
		Long id = saveItem.getId();
		System.out.println("Save Test...id is: " + id);
		assertNotNull(id);
	}
	

	private Collection<MusicItem> grabBatch() {
		Collection<MusicItem> batch = new ArrayList<MusicItem>();
		add(batch, "New One", "New", "2013-01-04", "13.99", MusicCategory.RAP);
		add(batch, "Another New One", "Newer", "2013-02-05", "14.99", MusicCategory.POP);
		batch.add(null);
		return batch;
	}

	private void add(Collection<MusicItem> batch, String title, String artist, String releaseDate,String price, MusicCategory musicCategory) {
		batch.add(new MusicItem(title, artist, releaseDate,new BigDecimal(price), musicCategory));
	}

}
